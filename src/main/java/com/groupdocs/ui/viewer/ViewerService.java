package com.groupdocs.ui.viewer;

import com.groupdocs.ui.model.response.FileDescriptionEntity;
import com.groupdocs.ui.model.response.LoadDocumentEntity;
import com.groupdocs.ui.model.response.PageDescriptionEntity;
import com.groupdocs.ui.model.request.LoadDocumentPageRequest;
import com.groupdocs.ui.model.request.LoadDocumentRequest;
import com.groupdocs.ui.viewer.model.request.RotateDocumentPagesRequest;
import com.groupdocs.ui.viewer.model.response.RotatedPageEntity;

import java.util.List;

public interface ViewerService {

    /**
     * Get configuration for viewer module
     *
     * @return viewer configuration
     */
    ViewerConfiguration getViewerConfiguration();

    /**
     * Get list of files in directory
     *
     * @param path specified directory
     * @return list of file's descriptions
     */
    List<FileDescriptionEntity> getFileList(String path);

    /**
     * Get document description
     *
     * @param loadDocumentRequest document data for loading
     * @param allPages to load all pages data of the document
     * @return document info container
     */
    LoadDocumentEntity loadDocument(LoadDocumentRequest loadDocumentRequest, boolean allPages);

    /**
     * Get document page
     *
     * @param loadDocumentPageRequest document page request data
     * @return document page info
     */
    PageDescriptionEntity loadDocumentPage(LoadDocumentPageRequest loadDocumentPageRequest);

    /**
     * Rotate document pages
     *
     * @param rotateDocumentPagesRequest rotate pages request info
     * @return list of rotated pages
     */
    List<RotatedPageEntity> rotateDocumentPages(RotateDocumentPagesRequest rotateDocumentPagesRequest);
}
