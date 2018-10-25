package com.groupdocs.ui.viewer;

import com.groupdocs.ui.config.GlobalConfiguration;
import com.groupdocs.ui.exception.TotalGroupDocsException;
import com.groupdocs.ui.model.request.LoadDocumentPageRequest;
import com.groupdocs.ui.model.request.LoadDocumentRequest;
import com.groupdocs.ui.model.response.FileDescriptionEntity;
import com.groupdocs.ui.model.response.LoadedPageEntity;
import com.groupdocs.ui.viewer.model.request.RotateDocumentPagesRequest;
import com.groupdocs.ui.viewer.model.response.RotatedPageEntity;
import com.groupdocs.viewer.config.ViewerConfig;
import com.groupdocs.viewer.converter.options.HtmlOptions;
import com.groupdocs.viewer.converter.options.ImageOptions;
import com.groupdocs.viewer.domain.FileDescription;
import com.groupdocs.viewer.domain.containers.DocumentInfoContainer;
import com.groupdocs.viewer.domain.containers.FileListContainer;
import com.groupdocs.viewer.domain.options.DocumentInfoOptions;
import com.groupdocs.viewer.domain.options.FileListOptions;
import com.groupdocs.viewer.domain.options.RotatePageOptions;
import com.groupdocs.viewer.exception.GroupDocsViewerException;
import com.groupdocs.viewer.exception.InvalidPasswordException;
import com.groupdocs.viewer.handler.ViewerHtmlHandler;
import com.groupdocs.viewer.handler.ViewerImageHandler;
import com.groupdocs.viewer.licensing.License;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ViewerServiceImpl implements ViewerService {

    private static final Logger logger = LoggerFactory.getLogger(ViewerServiceImpl.class);

    @Autowired
    private GlobalConfiguration globalConfiguration;
    @Autowired
    private ViewerConfiguration viewerConfiguration;

    private ViewerHtmlHandler viewerHtmlHandler;
    private ViewerImageHandler viewerImageHandler;

    /**
     * Initializing fields after creating configuration objects
     */
    @PostConstruct
    public void init() {
        // check files directory
        if (StringUtils.isEmpty(viewerConfiguration.getFilesDirectory())) {
            logger.error("Files directory must be specified!");
            throw new IllegalStateException("Files directory must be specified!");
        }

        try {
            // set GroupDocs license
            License license = new License();
            license.setLicense(globalConfiguration.getApplication().getLicensePath());
        } catch (Throwable throwable) {
            logger.error("Can not verify Viewer license!");
        }

        try {
            // create viewer application configuration
            ViewerConfig config = new ViewerConfig();
            config.setStoragePath(viewerConfiguration.getFilesDirectory());
            config.setUseCache(viewerConfiguration.isCache());
            config.getFontDirectories().add(viewerConfiguration.getFontsDirectory());

            // initialize total instance for the HTML mode
            viewerHtmlHandler = new ViewerHtmlHandler(config);

            // initialize total instance for the Image mode
            viewerImageHandler = new ViewerImageHandler(config);
        } catch (Throwable throwable) {
            logger.error("Viewer wasn't initiate properly!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FileDescriptionEntity> getFileList(String path) {
        // get file list from storage path
        FileListOptions fileListOptions = new FileListOptions(path);
        // get temp directory name
        String tempDirectoryName =  new com.groupdocs.viewer.config.ViewerConfig().getCacheFolderName();
        try {
            FileListContainer fileListContainer = viewerImageHandler.getFileList(fileListOptions);

            ArrayList<FileDescriptionEntity> fileList = new ArrayList<>();
            // parse files/folders list
            for (FileDescription fd : fileListContainer.getFiles()) {
                FileDescriptionEntity fileDescription = new FileDescriptionEntity();
                fileDescription.setGuid(fd.getGuid());
                // check if current file/folder is temp directory or is hidden
                if (tempDirectoryName.equals(fd.getName()) || new File(fileDescription.getGuid()).isHidden()) {
                    // ignore current file and skip to next one
                    continue;
                } else {
                    // set file/folder name
                    fileDescription.setName(fd.getName());
                }
                // set file type
                fileDescription.setDocType(fd.getDocumentType());
                // set is directory true/false
                fileDescription.setDirectory(fd.isDirectory());
                // set file size
                fileDescription.setSize(fd.getSize());
                // add object to array list
                fileList.add(fileDescription);
            }
            return fileList;
        } catch (Exception ex){
            logger.error("Exception in getting file list", ex);
            throw new TotalGroupDocsException(ex.getMessage(), ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocumentInfoContainer loadDocument(LoadDocumentRequest loadDocumentRequest) {
        // set request parameters
        String documentGuid = loadDocumentRequest.getGuid();
        String password = loadDocumentRequest.getPassword();
        try {
            // check if documentGuid contains path or only file name
            File file = new File(documentGuid);
            if (StringUtils.isEmpty(file.getAbsolutePath())) {
                documentGuid = viewerConfiguration.getFilesDirectory() + File.separator + documentGuid;
            } else {
                documentGuid = file.getAbsolutePath();
            }
            // get document info options
            DocumentInfoOptions documentInfoOptions = new DocumentInfoOptions(documentGuid);
            // set password for protected document
            if (!StringUtils.isEmpty(password)) {
                documentInfoOptions.setPassword(password);
            }
            // get document info container
            DocumentInfoContainer documentInfoContainer;
            if (loadDocumentRequest.getHtmlMode()) {
                documentInfoContainer = viewerHtmlHandler.getDocumentInfo(documentGuid, documentInfoOptions);
            } else {
                documentInfoContainer = viewerImageHandler.getDocumentInfo(documentGuid, documentInfoOptions);
            }
            // return document description
            return documentInfoContainer;
        } catch (GroupDocsViewerException ex){
            // Set exception message
            String message = ex.getMessage();
            if (GroupDocsViewerException.class.isAssignableFrom(InvalidPasswordException.class) && StringUtils.isEmpty(password)) {
                message = "Password Required";
            } else if(GroupDocsViewerException.class.isAssignableFrom(InvalidPasswordException.class) && !StringUtils.isEmpty(password)) {
                message = "Incorrect password";
            }
            logger.error(message, ex);
            throw new TotalGroupDocsException(message, ex);
        } catch (Exception ex){
            logger.error("Exception in loading document", ex);
            throw new TotalGroupDocsException(ex.getMessage(), ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoadedPageEntity loadDocumentPage(LoadDocumentPageRequest loadDocumentPageRequest) {
        // set request parameters
        String documentGuid = loadDocumentPageRequest.getGuid();
        Integer pageNumber = loadDocumentPageRequest.getPage();
        String password = loadDocumentPageRequest.getPassword();
        try {
            LoadedPageEntity loadedPage = new LoadedPageEntity();
            String angle;
            // set options
            if (loadDocumentPageRequest.getHtmlMode()) {
                HtmlOptions htmlOptions = new HtmlOptions();
                htmlOptions.setPageNumber(pageNumber);
                htmlOptions.setCountPagesToRender(1);
                htmlOptions.setResourcesEmbedded(true);
                // set password for protected document
                if (!StringUtils.isEmpty(password)) {
                    htmlOptions.setPassword(password);
                }
                // get page HTML
                loadedPage.setPageHtml(viewerHtmlHandler.getPages(documentGuid, htmlOptions).get(0).getHtmlContent());
                // get page rotation angle
                angle = String.valueOf(viewerHtmlHandler.getDocumentInfo(documentGuid).getPages().get(pageNumber - 1).getAngle());
            } else {
                ImageOptions imageOptions = new ImageOptions();
                imageOptions.setPageNumber(pageNumber);
                imageOptions.setCountPagesToRender(1);
                // set password for protected document
                if (!StringUtils.isEmpty(password)) {
                    imageOptions.setPassword(password);
                }
                // get page image
                byte[] bytes = IOUtils.toByteArray(viewerImageHandler.getPages(documentGuid, imageOptions).get(0).getStream());
                // encode ByteArray into String
                String encodedImage = new String(Base64.getEncoder().encode(bytes));
                loadedPage.setPageImage(encodedImage);
                // get page rotation angle
                angle = String.valueOf(viewerImageHandler.getDocumentInfo(documentGuid).getPages().get(pageNumber - 1).getAngle());
            }
            loadedPage.setAngle(angle);
            // return loaded page object
            return loadedPage;
        } catch (Exception ex){
            logger.error("Exception in loading document page", ex);
            throw new TotalGroupDocsException(ex.getMessage(), ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RotatedPageEntity> rotateDocumentPages(RotateDocumentPagesRequest rotateDocumentPagesRequest) {
        // set request parameters
        String documentGuid = rotateDocumentPagesRequest.getGuid();
        List<Integer> pages = rotateDocumentPagesRequest.getPages();
        String password = rotateDocumentPagesRequest.getPassword();
        Integer angle = rotateDocumentPagesRequest.getAngle();
        try {
            // a list of the rotated pages info
            ArrayList<RotatedPageEntity> rotatedPages = new ArrayList<>();
            // rotate pages
            for (int i = 0; i < pages.size(); i++) {
                int pageNumber = pages.get(i);
                // prepare rotated page info object
                RotatedPageEntity rotatedPage = new RotatedPageEntity();
                RotatePageOptions rotateOptions = new RotatePageOptions(pageNumber, angle);
                // perform page rotation
                String resultAngle;
                // set password for protected document
                if (!StringUtils.isEmpty(password)) {
                    rotateOptions.setPassword(password);
                }
                // rotate page
                if(rotateDocumentPagesRequest.getHtmlMode()) {
                    viewerHtmlHandler.rotatePage(documentGuid, rotateOptions);
                    resultAngle = String.valueOf(viewerHtmlHandler.getDocumentInfo(documentGuid).getPages().get(pageNumber - 1).getAngle());
                } else {
                    viewerImageHandler.rotatePage(documentGuid, rotateOptions);
                    resultAngle = String.valueOf(viewerImageHandler.getDocumentInfo(documentGuid).getPages().get(pageNumber - 1).getAngle());
                }
                // add rotated page number
                rotatedPage.setPageNumber(pageNumber);
                // add rotated page angle
                rotatedPage.setAngle(resultAngle);
                // add rotated page object into resulting list
                rotatedPages.add(rotatedPage);
            }
            return rotatedPages;
        }catch (Exception ex){
            logger.error("Exception in rotating document page", ex);
            throw new TotalGroupDocsException(ex.getMessage(), ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewerConfiguration getViewerConfiguration() {
        return viewerConfiguration;
    }

    public void setViewerConfiguration(ViewerConfiguration viewerConfiguration) {
        this.viewerConfiguration = viewerConfiguration;
    }
}
