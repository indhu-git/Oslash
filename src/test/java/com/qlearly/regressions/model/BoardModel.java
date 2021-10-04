package com.qlearly.regressions.model;

public class BoardModel extends ParentModel<ColumnModel> {

    private String folderName;
    public BoardModel(String folderName, String title) {
        this.title = title;
        this.folderName = folderName;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public void addColumn(ColumnModel columnModal) {
        children.add(columnModal);
    }
}
