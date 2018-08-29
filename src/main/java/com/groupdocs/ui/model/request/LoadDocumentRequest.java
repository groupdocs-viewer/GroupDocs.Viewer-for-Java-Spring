package com.groupdocs.ui.model.request;

public class LoadDocumentRequest {

    private String guid;
    private Boolean htmlMode;
    private String password;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Boolean getHtmlMode() {
        return htmlMode;
    }

    public void setHtmlMode(Boolean htmlMode) {
        this.htmlMode = htmlMode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
