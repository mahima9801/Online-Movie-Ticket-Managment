package com.capgemini.onlinemovieticketsystem.dto;

import java.util.List;

public class ScreenDetailsDto {

    private int screenId;
    private int theaterId;
    private String screenName;
    private int rows;
    private int columns;
    private List<Integer> showList;

    public int getScreenId() {
        return screenId;
    }
    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }
    public int getTheaterId() {
        return theaterId;
    }
    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }
    public String getScreenName() {
        return screenName;
    }
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public int getRows() {
        return rows;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    public int getColumns() {
        return columns;
    }
    public void setColumns(int columns) {
        this.columns = columns;
    }
    public List<Integer> getShowList() {
        return showList;
    }
    public void setShowList(List<Integer> showList) {
        this.showList = showList;
    }
}
