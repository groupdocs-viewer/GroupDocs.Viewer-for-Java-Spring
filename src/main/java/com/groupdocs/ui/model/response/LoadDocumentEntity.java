package com.groupdocs.ui.model.response;

import java.util.List;

public class LoadDocumentEntity {
    /**
     * Document Guid
     */
    private String guid;
    /**
     * list of pages
     */
    private List<PageDescriptionEntity> pages;

    /**
     * Restriction for printing pdf files in viewer
     */
    private Boolean printAllowed = true;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public List<PageDescriptionEntity> getPages() {
        return pages;
    }

    public void setPages(List<PageDescriptionEntity> pages) {
        this.pages = pages;
    }

    public Boolean getPrintAllowed() {
        return printAllowed;
    }

    public void setPrintAllowed(Boolean printAllowed) {
        this.printAllowed = printAllowed;
    }
}
