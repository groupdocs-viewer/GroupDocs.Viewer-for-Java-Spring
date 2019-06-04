package com.groupdocs.ui.viewer;

import com.groupdocs.ui.config.DefaultDirectories;
import com.groupdocs.ui.config.GlobalConfiguration;
import com.groupdocs.ui.exception.TotalGroupDocsException;
import com.groupdocs.ui.model.request.LoadDocumentPageRequest;
import com.groupdocs.ui.model.request.LoadDocumentRequest;
import com.groupdocs.ui.model.response.FileDescriptionEntity;
import com.groupdocs.ui.model.response.LoadDocumentEntity;
import com.groupdocs.ui.model.response.PageDescriptionEntity;
import com.groupdocs.ui.viewer.model.request.RotateDocumentPagesRequest;
import com.groupdocs.ui.viewer.model.response.RotatedPageEntity;
import com.groupdocs.viewer.config.ViewerConfig;
import com.groupdocs.viewer.converter.options.HtmlOptions;
import com.groupdocs.viewer.converter.options.ImageOptions;
import com.groupdocs.viewer.domain.FileDescription;
import com.groupdocs.viewer.domain.Page;
import com.groupdocs.viewer.domain.PageData;
import com.groupdocs.viewer.domain.containers.DocumentInfoContainer;
import com.groupdocs.viewer.domain.containers.FileListContainer;
import com.groupdocs.viewer.domain.containers.PdfDocumentInfoContainer;
import com.groupdocs.viewer.domain.html.PageHtml;
import com.groupdocs.viewer.domain.image.PageImage;
import com.groupdocs.viewer.domain.options.DocumentInfoOptions;
import com.groupdocs.viewer.domain.options.FileListOptions;
import com.groupdocs.viewer.domain.options.RotatePageOptions;
import com.groupdocs.viewer.exception.GroupDocsViewerException;
import com.groupdocs.viewer.handler.ViewerHandler;
import com.groupdocs.viewer.handler.ViewerHtmlHandler;
import com.groupdocs.viewer.handler.ViewerImageHandler;
import com.groupdocs.viewer.licensing.License;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.groupdocs.ui.util.Utils.getExceptionMessage;
import static com.groupdocs.ui.util.Utils.getStringFromStream;
import static com.groupdocs.ui.viewer.ViewerOptionsFactory.*;

@Service
public class ViewerServiceImpl implements ViewerService {

    private static final Logger logger = LoggerFactory.getLogger(ViewerServiceImpl.class);
    public static final String PDF = "pdf";

    @Autowired
    private GlobalConfiguration globalConfiguration;
    @Autowired
    private ViewerConfiguration viewerConfiguration;

    private ViewerHandler viewerHandler;

    /**
     * Initializing fields after creating configuration objects
     */
    @PostConstruct
    public void init() {
        setLicense();

        configure();
    }

    private void configure() {
        try {
            // create viewer application configuration
            ViewerConfig config = getViewerConfig();

            if (viewerConfiguration.isHtmlMode()) {
                // initialize total instance for the HTML mode
                viewerHandler = new ViewerHtmlHandler(config);
            } else {
                // initialize total instance for the Image mode
                viewerHandler = new ViewerImageHandler(config);
            }
        } catch (Throwable throwable) {
            logger.error("Viewer wasn't initiate properly!");
        }
    }

    private ViewerConfig getViewerConfig() {
        ViewerConfig config = new ViewerConfig();
        String filesDirectory = viewerConfiguration.getFilesDirectory();
        if (!StringUtils.isEmpty(filesDirectory) && !filesDirectory.endsWith(File.separator)) {
            filesDirectory = filesDirectory + File.separator;
        }
        config.setStoragePath(filesDirectory);
        config.setEnableCaching(viewerConfiguration.isCache());
        if (!StringUtils.isEmpty(viewerConfiguration.getFontsDirectory())) {
            config.getFontDirectories().add(viewerConfiguration.getFontsDirectory());
        }
        return config;
    }

    private void setLicense() {
        try {
            // set GroupDocs license
            License license = new License();
            license.setLicense(globalConfiguration.getApplication().getLicensePath());
        } catch (Throwable throwable) {
            logger.error("Can not verify Viewer license!");
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
        String tempDirectoryName = new com.groupdocs.viewer.config.ViewerConfig().getCacheFolderName();
        try {
            FileListContainer fileListContainer = viewerHandler.getFileList(fileListOptions);

            List<FileDescriptionEntity> fileList = new ArrayList<>();
            // parse files/folders list
            for (FileDescription fd : fileListContainer.getFiles()) {
                if (!tempDirectoryName.equals(fd.getName()) && !new File(fd.getGuid()).isHidden()) {
                    FileDescriptionEntity fileDescription = new FileDescriptionEntity();
                    fileDescription.setGuid(fd.getGuid());
                    // check if current file/folder is temp directory or is hidden
                    fileDescription.setName(fd.getName());
                    // set file type
                    fileDescription.setDocType(fd.getDocumentType());
                    // set is directory true/false
                    fileDescription.setDirectory(fd.isDirectory());
                    // set file size
                    fileDescription.setSize(fd.getSize());
                    // add object to array list
                    fileList.add(fileDescription);
                }
            }
            return fileList;
        } catch (Exception ex) {
            logger.error("Exception in getting file list", ex);
            throw new TotalGroupDocsException(ex.getMessage(), ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoadDocumentEntity loadDocument(LoadDocumentRequest loadDocumentRequest, boolean loadAllPages) {
        // set request parameters
        String documentGuid = loadDocumentRequest.getGuid();
        String password = loadDocumentRequest.getPassword();
        try {
            // check if documentGuid contains path or only file name
            if (!DefaultDirectories.isAbsolutePath(documentGuid)) {
                documentGuid = viewerConfiguration.getFilesDirectory() + File.separator + documentGuid;
            }
            // get document info options
            DocumentInfoOptions documentInfoOptions = getDocumentInfoOptions(password);
            // get document info container
            DocumentInfoContainer documentInfoContainer = viewerHandler.getDocumentInfo(documentGuid, documentInfoOptions);

            // return document description
            return getLoadDocumentEntity(documentGuid, password, documentInfoContainer, loadAllPages);
        } catch (GroupDocsViewerException ex) {
            throw new TotalGroupDocsException(getExceptionMessage(password, ex), ex);
        } catch (Exception ex) {
            logger.error("Exception in loading document", ex);
            throw new TotalGroupDocsException(ex.getMessage(), ex);
        }
    }

    private LoadDocumentEntity getLoadDocumentEntity(String documentGuid, String password, DocumentInfoContainer documentInfoContainer, boolean loadAllPages) throws Exception {
        LoadDocumentEntity loadDocumentEntity = new LoadDocumentEntity();
        loadDocumentEntity.setGuid(documentGuid);

        List<Page> pagesData = loadAllPages ? getPagesData(documentGuid, password) : Collections.EMPTY_LIST;
        List<PageDescriptionEntity> pages = getPageDescriptionEntities(documentInfoContainer.getPages(), pagesData);
        loadDocumentEntity.setPages(pages);

        if (viewerConfiguration.getPrintAllowed() && PDF.equals(FilenameUtils.getExtension(documentGuid)) && documentInfoContainer instanceof PdfDocumentInfoContainer) {
            loadDocumentEntity.setPrintAllowed(((PdfDocumentInfoContainer) documentInfoContainer).getPrintingAllowed());
        }
        return loadDocumentEntity;
    }

    private List<Page> getPagesData(String documentGuid, String password) throws Exception {
        if (viewerConfiguration.isHtmlMode()) {
            HtmlOptions htmlOptions = createCommonHtmlOptions(password, viewerConfiguration.getWatermarkText());
            return viewerHandler.getPages(documentGuid, htmlOptions);
        } else {
            ImageOptions imageOptions = createCommonImageOptions(password, viewerConfiguration.getWatermarkText());
            return viewerHandler.getPages(documentGuid, imageOptions);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageDescriptionEntity loadDocumentPage(LoadDocumentPageRequest loadDocumentPageRequest) {
        String documentGuid = loadDocumentPageRequest.getGuid();
        Integer pageNumber = loadDocumentPageRequest.getPage();
        String password = loadDocumentPageRequest.getPassword();
        try {
            DocumentInfoOptions documentInfoOptions = getDocumentInfoOptions(password);
            PageData pageData = viewerHandler.getDocumentInfo(documentGuid, documentInfoOptions).getPages().get(pageNumber - 1);
            PageDescriptionEntity loadedPage = getPageDescriptionEntity(pageData);
            // set options
            if (viewerConfiguration.isHtmlMode()) {
                HtmlOptions htmlOptions = createHtmlOptions(pageNumber, password, viewerConfiguration.getWatermarkText());
                // get page HTML
                PageHtml page = (PageHtml) viewerHandler.getPages(documentGuid, htmlOptions).get(0);
                loadedPage.setData(page.getHtmlContent());
            } else {
                ImageOptions imageOptions = createImageOptions(pageNumber, password, viewerConfiguration.getWatermarkText());
                // get page image
                PageImage page = (PageImage) viewerHandler.getPages(documentGuid, imageOptions).get(0);
                loadedPage.setData(getStringFromStream(page.getStream()));
            }
            // return loaded page object
            return loadedPage;
        } catch (Exception ex) {
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
            // get document info options
            DocumentInfoOptions documentInfoOptions = getDocumentInfoOptions(password);
            // a list of the rotated pages info
            List<RotatedPageEntity> rotatedPages = new ArrayList<>();
            // rotate pages
            for (int i = 0; i < pages.size(); i++) {
                int pageNumber = pages.get(i);
                RotatePageOptions rotateOptions = new RotatePageOptions(pageNumber, angle);
                // set password for protected document
                if (!StringUtils.isEmpty(password)) {
                    rotateOptions.setPassword(password);
                }
                // rotate page
                viewerHandler.rotatePage(documentGuid, rotateOptions);
                int resultAngle = viewerHandler.getDocumentInfo(documentGuid, documentInfoOptions).getPages().get(pageNumber - 1).getAngle();
                // prepare rotated page info object
                RotatedPageEntity rotatedPage = getRotatedPageEntity(pageNumber, resultAngle);
                // add rotated page object into resulting list
                rotatedPages.add(rotatedPage);
            }
            return rotatedPages;
        } catch (Exception ex) {
            logger.error("Exception in rotating document page", ex);
            throw new TotalGroupDocsException(ex.getMessage(), ex);
        }
    }

    private DocumentInfoOptions getDocumentInfoOptions(String password) {
        DocumentInfoOptions documentInfoOptions = new DocumentInfoOptions();
        // set password for protected document
        if (!StringUtils.isEmpty(password)) {
            documentInfoOptions.setPassword(password);
        }
        return documentInfoOptions;
    }

    private RotatedPageEntity getRotatedPageEntity(int pageNumber, int resultAngle) {
        RotatedPageEntity rotatedPage = new RotatedPageEntity();
        // add rotated page number
        rotatedPage.setPageNumber(pageNumber);
        // add rotated page angle
        rotatedPage.setAngle(resultAngle);
        return rotatedPage;
    }

    private List<PageDescriptionEntity> getPageDescriptionEntities(List<PageData> containerPages, List<Page> pagesData) throws IOException {
        List<PageDescriptionEntity> pages = new ArrayList<>();
        for (int i = 0; i < containerPages.size(); i++) {
            PageData page = containerPages.get(i);
            PageDescriptionEntity pageDescriptionEntity = getPageDescriptionEntity(page);
            if (!pagesData.isEmpty()) {
                Page pageData = pagesData.get(i);
                pageDescriptionEntity.setData(getPageData(pageData));
            }
            pages.add(pageDescriptionEntity);
        }
        return pages;
    }

    private String getPageData(Page pageData) throws IOException {
        if (viewerConfiguration.isHtmlMode()) {
            return ((PageHtml) pageData).getHtmlContent();
        } else {
            return getStringFromStream(((PageImage) pageData).getStream());
        }
    }

    private PageDescriptionEntity getPageDescriptionEntity(PageData page) {
        PageDescriptionEntity pageDescriptionEntity = new PageDescriptionEntity();
        pageDescriptionEntity.setNumber(page.getNumber());
        pageDescriptionEntity.setAngle(page.getAngle());
        pageDescriptionEntity.setHeight(page.getHeight());
        pageDescriptionEntity.setWidth(page.getWidth());
        return pageDescriptionEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewerConfiguration getViewerConfiguration() {
        return viewerConfiguration;
    }
}
