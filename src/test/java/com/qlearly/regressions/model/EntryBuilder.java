package com.qlearly.regressions.model;

public class EntryBuilder {

    public static EntryModel toDOEntry(String title, boolean isCompleted){
        return new EntryModel(EntryType.TODO, title,null,null , isCompleted);
    }

    public static EntryModel linkEntry(String name, String link){
        return new EntryModel(EntryType.LINK, name, link, null, true);
    }

    public static EntryModel noteEntry(){
        return new EntryModel(EntryType.LINK, null, null, null, true);
    }
}
