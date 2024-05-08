package edu.upvictoria.fpoo;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.nio.file.NoSuchFileException;
import java.nio.file.NotDirectoryException;

public class SQL {
    public String clean(String line, String keyword) throws StringIndexOutOfBoundsException {
        int endOfKeyword = line.indexOf(keyword) + keyword.length();
        int semicolon = line.indexOf(";");

        try {
            line = line.substring(endOfKeyword + 1, semicolon);
        } catch (StringIndexOutOfBoundsException e) {
            throw new StringIndexOutOfBoundsException("STRING OUT OF BOUNDS");
        }

        return line;
    }

    public File handleUse(String line, String keyword) throws FileSystemException, StringIndexOutOfBoundsException, FileNotFoundException {
        String givenPath;

        try {
            givenPath = clean(line,keyword);
        } catch (StringIndexOutOfBoundsException e){
            throw new StringIndexOutOfBoundsException(e.getMessage());
        }

        File database = new File(new File("").getAbsolutePath() + "/" + givenPath);

        if(!database.exists()){
            throw new FileNotFoundException();
        }

        if(!database.isDirectory()){
            throw new NotDirectoryException(givenPath);
        }

        if(!database.getName().endsWith("_DB")){
            throw new NoSuchFileException(givenPath);
        }

        return database;
    }

    public void handleShowTables(String line, String keyword){
    }

    public void handleCreateTable(String line, String keyword){
    }

    public void handleCreateDatabase(String line, String keyword){
    }

    public void handleDropTable(String line, String keyword){
    }

    public void handleDropDatabase(String line, String keyword){
    }

    public void handleInsertInto(String line, String keyword){
    }

    public void handleValues(String line, String keyword){
    }

    public void handleDeleteFrom(String line, String keyword){
    }

    public void handleFrom(String line, String keyword){
    }

    public void handleWhere(String line, String keyword){
    }

    public void handleAnd(String line, String keyword){
    }

    public void handleOr(String line, String keyword){
    }

    public void handleNot(String line, String keyword){
    }

    public void handleUpdate(String line, String keyword){
    }

    public void handleSet(String line, String keyword){
    }

    public void handleSelect(String line, String keyword){
    }

    public void handleAs(String line, String keyword){
    }

    public void handleOrder(String line, String keyword){
    }

    public void handleGroup(String line, String keyword){
    }

    public void handleLimit(String line, String keyword){
    }

    public void handleNull(String line, String keyword){
    }

    public void handlePrimary(String line, String keyword){
    }

    public void handleNumber(String line, String keyword){
    }

    public void handleVarchar(String line, String keyword){
    }

    public void handleChar(String line, String keyword){
    }

    public void handleBoolean(String line, String keyword){
    }

    public void handleDate(String line, String keyword){
    }

    public void handleIn(String line, String keyword){
    }
}
