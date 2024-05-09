package edu.upvictoria.fpoo;

import java.io.File;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Objects;

public class Database {
    private File dbFile;
    private final ArrayList<Table> tables = new ArrayList<>();

    public void retrieveTables() throws RuntimeException {
        try {
            for(File file : Objects.requireNonNull(this.dbFile.listFiles())){
                Table table = new Table(file);
                addTable(table);
            }
        } catch (FileSystemException e) {
            throw new RuntimeException(e.getMessage());
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

    public void printTables(){
        for(Table table : this.tables){
            System.out.println("\t" + table.getTableName());
        }
    }

    public void addTable(Table table) {
        this.tables.add(table);
    }
}
