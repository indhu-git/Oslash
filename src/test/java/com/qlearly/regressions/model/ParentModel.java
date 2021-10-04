package com.qlearly.regressions.model;

import java.util.List;

public abstract class ParentModel<T> {
    protected String title;
    protected List<T> children;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }
}
