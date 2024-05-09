package edu.upvictoria.fpoo;

import edu.upvictoria.fpoo.exceptions.NotFileException;
import edu.upvictoria.fpoo.exceptions.WrongFileExtensionException;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class SQL {
    public String clean(String line, String keyword) throws StringIndexOutOfBoundsException {
        int endOfKeyword = line.indexOf(keyword) + keyword.length();
        int semicolon = line.indexOf(";");

        try {
            line = line.substring(endOfKeyword + 1, semicolon).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new StringIndexOutOfBoundsException("STRING OUT OF BOUNDS");
        }

        return line;
    }

    public ArrayList<String> splitValues(String line) {
        ArrayList<String> columns = new ArrayList<>();
        String tableName;

        try {
            tableName = line.substring(0, line.indexOf("(")).trim();
        } catch (StringIndexOutOfBoundsException e){
            throw new StringIndexOutOfBoundsException("TABLE NAME NOT FOUND / RECOGNIZED");
        }

        columns.add(tableName);

        String[] values = line.split(",");
        for(String value : values){
            value = value.substring(0,value.indexOf(" "));
            columns.add(value);
        }

        return columns;
    }

    public File handleUse(String line, String keyword) throws FileSystemException, StringIndexOutOfBoundsException, FileNotFoundException {
        String givenPath;

        try {
            givenPath = clean(line,keyword);
        } catch (StringIndexOutOfBoundsException e){
            throw new StringIndexOutOfBoundsException(e.getMessage());
        }

        File database = new File(Paths.get("").toAbsolutePath().resolve(givenPath).toString());

        if(!database.exists()){
            throw new FileNotFoundException();
        }

        if(!database.isDirectory()){
            throw new NotDirectoryException("GIVEN PATH IS NOT A DIRECTORY: " + givenPath);
        }

        if(!database.getName().endsWith("_DB")){
            throw new NoSuchFileException(givenPath);
        }

        return database;
    }

    public void handleCreateTable(String line, String keyword){

    }

    public void handleCreateDatabase(String line, String keyword) throws FileSystemException {
        String givenPath;

        try {
            givenPath = clean(line,keyword);
        } catch (StringIndexOutOfBoundsException e){
            throw new StringIndexOutOfBoundsException(e.getMessage());
        }

        File database = new File(Paths.get("").toAbsolutePath().resolve(givenPath).toString());

        if(database.exists()){
            throw new FileAlreadyExistsException(givenPath);
        }

        if(!database.getName().endsWith("_DB")){
            throw new NoSuchFileException(givenPath);
        }

        if(!database.getParentFile().canWrite()){
            throw new AccessDeniedException("NO PERMISSION IN GIVEN PATH: " + givenPath);
        }

        if(!database.mkdir()){
            throw new SecurityException("FAILED TO CREATE DIRECTORY AT " + givenPath);
        }
    }

    public void handleDropTable(String line, String keyword, Database database) throws IOException {
        String givenName;

        try {
            givenName = clean(line, keyword);
        } catch (StringIndexOutOfBoundsException e) {
            throw new StringIndexOutOfBoundsException(e.getMessage());
        }

        for (Table table : database.getTables()) {
            if (table.getTableName().equals(givenName)) {

                System.out.print("DO YOU REALLY WANT TO DELETE THE TABLE? Y/N\n ~ ");

                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    String decision = reader.readLine().toUpperCase();

                    if(!decision.equals("Y")){
                        throw new IOException("USER REFUSED DROP TABLE PROCESS");
                    }
                } catch (IOException e) {
                    throw new IOException(e.getMessage());
                }

                if (!table.getTableFile().delete()) {
                    throw new FileSystemException("COULD NOT DELETE THE FILE AT: " + table.getTableFile().getAbsolutePath());
                } else {
                    return;
                }
            }
        }

        throw new FileNotFoundException("FILE NOT FOUND");
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

    public void handleUpdate(String line, String keyword){
    }

    public void handleSet(String line, String keyword){
    }

    public void handleSelect(String line, String keyword){
    }
}
