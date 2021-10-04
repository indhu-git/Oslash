package com.qlearly.regressions.model;

import java.util.LinkedList;
import java.util.List;

public class ColumnModel extends ParentModel<EntryModel> {

    private BoardModel boardModel;
    private List<EntryModel> entryList = new LinkedList<>();

    public ColumnModel(BoardModel boardModel, String title) {
        this.boardModel = boardModel;
        this.title = title;
    }

    public ColumnModel(BoardModel boardModel, String title, List<EntryModel> entryList) {
        this.boardModel = boardModel;
        this.title = title;
        this.entryList = entryList;
    }

    public void setBoardModel(BoardModel boardModel) {
        this.boardModel = boardModel;
    }

    public void addLinkEntry(String name, String link){
         entryList.add(EntryBuilder.linkEntry(name, link));
    }

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public List<EntryModel> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<EntryModel> entryList) {
        this.entryList = entryList;
    }
}
