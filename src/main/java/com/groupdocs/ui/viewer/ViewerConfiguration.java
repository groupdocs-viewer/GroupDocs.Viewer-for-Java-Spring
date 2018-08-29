package com.groupdocs.ui.viewer;

import com.groupdocs.ui.config.CommonConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "viewer")
public class ViewerConfiguration extends CommonConfiguration {

    @Value("${filesDirectory}")
    private String filesDirectory;

    @Value("${fontsDirectory}")
    private String fontsDirectory;

    @Value("#{new Integer('${preloadPageCount}')}")
    private Integer preloadPageCount;

    @Value("#{new Boolean('${zoom}')}")
    private Boolean zoom;

    @Value("#{new Boolean('${search}')}")
    private Boolean search;

    @Value("#{new Boolean('${thumbnails}')}")
    private Boolean thumbnails;

    @Value("#{new Boolean('${rotate}')}")
    private Boolean rotate;

    @Value("${defaultDocument}")
    private String defaultDocument;

    @Value("#{new Boolean('${htmlMode}')}")
    private Boolean htmlMode;

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

    public String getDefaultDocument() {
        return defaultDocument;
    }

    public void setDefaultDocument(String defaultDocument) {
        this.defaultDocument = defaultDocument;
    }

    public Boolean isHtmlMode() {
        return htmlMode;
    }

    public void setHtmlMode(Boolean htmlMode) {
        this.htmlMode = htmlMode;
    }

    @Override
    public String toString() {
        return super.toString() +
                "ViewerConfiguration{" +
                "filesDirectory='" + filesDirectory + '\'' +
                ", fontsDirectory='" + fontsDirectory + '\'' +
                ", preloadPageCount=" + preloadPageCount +
                ", zoom=" + zoom +
                ", search=" + search +
                ", thumbnails=" + thumbnails +
                ", rotate=" + rotate +
                ", defaultDocument='" + defaultDocument + '\'' +
                ", htmlMode=" + htmlMode +
                '}';
    }
}
