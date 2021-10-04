package com.qlearly.regressions.model;

public class EntryModel extends ParentModel {
    private String link;
    private EntryType type;
    private String description;
    private boolean isCompleted;

    public EntryModel(EntryType type, String title, String link, String description, boolean isCompleted) {
        this.type = type;
        this.title = title;
        this.link = link;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public EntryType getType() {
        return type;
    }

    public void setType(EntryType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
