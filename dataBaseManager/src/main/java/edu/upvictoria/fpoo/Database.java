package edu.upvictoria.fpoo;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Database {
    private File dbFile;
    private ArrayList<Table> tables;

    public void retrieveTables(){
        for(File file : Objects.requireNonNull(this.dbFile.listFiles())){

        }
    }

    public void setDbFile(File dbFile) {
        this.dbFile = dbFile;
    }

    public File getDbFile() {
        return dbFile;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public void addTable(Table table) {
        this.tables.add(table);
    }
}
