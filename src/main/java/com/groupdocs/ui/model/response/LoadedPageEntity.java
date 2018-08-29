package com.groupdocs.ui.model.response;

/**
 * LoadedPageEntity
 *
 * @author Aspose Pty Ltd
 */
public class LoadedPageEntity {
    private String pageHtml;
    private String angle;
    private String pageImage;

    /**
     * Get page as html
     * @return page
     */
    public String getPageHtml() {
        return pageHtml;
    }

    /**
     * Set page html
     * @param pageHtml html page
     */
    public void setPageHtml(String pageHtml) {
        this.pageHtml = pageHtml;
    }

    /**
     * Get page rotation angle
     * @return angle (from 0 to 270 deg)
     */
    public String getAngle() {
        return angle;
    }

    /**
     * Set page rotation angle
     * @param angle angle (from 0 to 270 deg)
     */
    public void setAngle(String angle) {
        this.angle = angle;
    }

    /**
     * Get page as image
     * @return page
     */
    public String getPageImage() {
        return pageImage;
    }

    /**
     * Set page image
     * @param pageImage image page
     */
    public void setPageImage(String pageImage) {
        this.pageImage = pageImage;
    }

}
