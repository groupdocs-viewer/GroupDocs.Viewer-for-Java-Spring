package com.groupdocs.ui.viewer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "viewer")
public class ViewerConfiguration {

    @Value("${filesDirectory}")
    private String filesDirectory;

    @Value("${fontsDirectory}")
    private String fontsDirectory;

    @Value("#{new Integer('${preloadPageCount}')}")
    private Integer preloadPageCount;

    @Value("#{new Boolean('${zoom}')}")
    private Boolean zoom;

    @Value("#{new Boolean('${pageSelector}')}")
    private Boolean pageSelector;

    @Value("#{new Boolean('${search}')}")
    private Boolean search;

    @Value("#{new Boolean('${thumbnails}')}")
    private Boolean thumbnails;

    @Value("#{new Boolean('${rotate}')}")
    private Boolean rotate;

    @Value("#{new Boolean('${download}')}")
    private Boolean download;

    @Value("#{new Boolean('${upload}')}")
    private Boolean upload;

    @Value("#{new Boolean('${print}')}")
    private Boolean print;

    @Value("${defaultDocument}")
    private String defaultDocument;

    @Value("#{new Boolean('${browse}')}")
    private Boolean browse;

    @Value("#{new Boolean('${htmlMode}')}")
    private Boolean htmlMode;

    @Value("#{new Boolean('${rewrite}')}")
    private Boolean rewrite;

    public String getFilesDirectory() {
        return filesDirectory;
    }

    public void setFilesDirectory(String filesDirectory) {
        this.filesDirectory = filesDirectory;
    }

    public String getFontsDirectory() {
        return fontsDirectory;
    }

    public void setFontsDirectory(String fontsDirectory) {
        this.fontsDirectory = fontsDirectory;
    }

    public Integer getPreloadPageCount() {
        return preloadPageCount;
    }

    public void setPreloadPageCount(Integer preloadPageCount) {
        this.preloadPageCount = preloadPageCount;
    }

    public Boolean isZoom() {
        return zoom;
    }

    public void setZoom(Boolean zoom) {
        this.zoom = zoom;
    }

    public Boolean isPageSelector() {
        return pageSelector;
    }

    public void setPageSelector(Boolean pageSelector) {
        this.pageSelector = pageSelector;
    }

    public Boolean isSearch() {
        return search;
    }

    public void setSearch(Boolean search) {
        this.search = search;
    }

    public Boolean isThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Boolean thumbnails) {
        this.thumbnails = thumbnails;
    }

    public Boolean isRotate() {
        return rotate;
    }

    public void setRotate(Boolean rotate) {
        this.rotate = rotate;
    }

    public Boolean isDownload() {
        return download;
    }

    public void setDownload(Boolean download) {
        this.download = download;
    }

    public Boolean isUpload() {
        return upload;
    }

    public void setUpload(Boolean upload) {
        this.upload = upload;
    }

    public Boolean isPrint() {
        return print;
    }

    public void setPrint(Boolean print) {
        this.print = print;
    }

    public String getDefaultDocument() {
        return defaultDocument;
    }

    public void setDefaultDocument(String defaultDocument) {
        this.defaultDocument = defaultDocument;
    }

    public Boolean isBrowse() {
        return browse;
    }

    public void setBrowse(Boolean browse) {
        this.browse = browse;
    }

    public Boolean isHtmlMode() {
        return htmlMode;
    }

    public void setHtmlMode(Boolean htmlMode) {
        this.htmlMode = htmlMode;
    }

    public Boolean isRewrite() {
        return rewrite;
    }

    public void setRewrite(Boolean rewrite) {
        this.rewrite = rewrite;
    }

    @Override
    public String toString() {
        return "ViewerConfiguration{" +
                "filesDirectory='" + filesDirectory + '\'' +
                ", fontsDirectory='" + fontsDirectory + '\'' +
                ", preloadPageCount=" + preloadPageCount +
                ", zoom=" + zoom +
                ", pageSelector=" + pageSelector +
                ", search=" + search +
                ", thumbnails=" + thumbnails +
                ", rotate=" + rotate +
                ", download=" + download +
                ", upload=" + upload +
                ", print=" + print +
                ", defaultDocument='" + defaultDocument + '\'' +
                ", browse=" + browse +
                ", htmlMode=" + htmlMode +
                ", rewrite=" + rewrite +
                '}';
    }
}
