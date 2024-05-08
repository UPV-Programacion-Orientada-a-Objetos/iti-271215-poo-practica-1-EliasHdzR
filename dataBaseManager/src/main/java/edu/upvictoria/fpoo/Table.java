package edu.upvictoria.fpoo;

/**
 * OBJECT IS THE WAY
 */

import java.io.File;
import java.util.ArrayList;

public class Table {
    private File tableFile;
    ArrayList<ArrayList<Object>> data;

    public Table(File tableFile, ArrayList<ArrayList<Object>> data){
        this.tableFile = tableFile;
        this.data = data;
    }

    public Table(File tableFile){
        this.tableFile = tableFile;
    }

    public File getTableFile() {
        return tableFile;
    }

    public void setTableFile(File tableFile) {
        this.tableFile = tableFile;
    }

    public ArrayList<ArrayList<Object>> getColumns() {
        return data;
    }

    public void setColumns(ArrayList<ArrayList<Object>> data) {
        this.data = data;
    }
}
