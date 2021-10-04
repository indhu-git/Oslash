package com.qlearly.regressions.pages;

public enum Options {
    RENAME("Rename"),
    EDIT("Edit"),
    DELETE("Delete"),
    IMPORT("Import"),
    EXPORT("Export"),
    DUPLICATE("Duplicate"),
    CREATE_TASK("Create Task"),
    CREATE_NOTE("Create Note"),
    DELETE_COLUMN("Delete Column"),
    DELETE_ALL_TABS("Delete All Tabs"),
    BULK_EDIT("Bulk Edit"),
    MOVE_TO("Move To");

    String option;

    Options(String option) {
        this.option = option;
    }
}
