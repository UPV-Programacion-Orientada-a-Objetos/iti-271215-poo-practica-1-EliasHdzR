package edu.upvictoria.fpoo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.io.File;

public class Analyzer {
    private final ArrayList<String> keywords = new ArrayList<>();
    private final ArrayList<String> pseudoKeywords = new ArrayList<>();
    private final ArrayList<String> dataTypes = new ArrayList<>();
    private final ArrayList<String> dataModifiers = new ArrayList<>();
    private final ArrayList<String> relations = new ArrayList<>();

    private final SQL sql = new SQL();
    private Database database = new Database();


    public Analyzer(){
        keywords.add("USE");
        keywords.add("SHOW TABLES");
        keywords.add("CREATE DATABASE");
        keywords.add("CREATE TABLE");
        keywords.add("DROP DATABASE");
        keywords.add("DROP TABLE");
        keywords.add("INSERT INTO");
        keywords.add("VALUES");
        keywords.add("DELETE FROM");
        keywords.add("FROM");
        keywords.add("WHERE");
        keywords.add("UPDATE");
        keywords.add("SET");
        keywords.add("SELECT");

        pseudoKeywords.add("AND");
        pseudoKeywords.add("OR");
        pseudoKeywords.add("NOT");
        pseudoKeywords.add("AS");

        dataModifiers.add("NULL");

        relations.add("PRIMARY KEY");
        relations.add("FOREIGN KEY");

        dataTypes.add("NUMBER");
        dataTypes.add("VARCHAR");
        dataTypes.add("CHAR");
        dataTypes.add("BOOLEAN");
        dataTypes.add("DATE");
        dataTypes.add("INT");
    }

    public void analyzeSyntax(String line, int totalLines) throws Exception {
        boolean found = false;

        for(String keyword : keywords){
            if(line.startsWith(keyword)){
                found = true;

                try {
                    //lets get funky
                    switch (keyword) {
                        case "USE":
                            if(totalLines > 1){
                                throw new IOException("SYNTAX ERROR");
                            }

                            File dbFile = sql.handleUse(line, keyword);
                            refreshDB(dbFile);
                            break;

                        case "SHOW TABLES":
                            if(totalLines > 1 || line.length() > (keyword.length() + 1)){
                                throw new IOException("SYNTAX ERROR");
                            }

                            refreshDB(this.database.getDbFile());
                            this.database.printTables();
                            break;

                        case "CREATE TABLE": //WIP
                            sql.handleCreateTable(line, keyword);
                            break;

                        case "CREATE DATABASE":
                            if(totalLines > 1){
                                throw new IOException("SYNTAX ERROR");
                            }

                            sql.handleCreateDatabase(line, keyword);
                            break;

                        case "DROP TABLE":
                            if(totalLines > 1){
                                throw new IOException("SYNTAX ERROR");
                            }

                            sql.handleDropTable(line, keyword, this.database);
                            refreshDB(this.database.getDbFile());
                            break;

                        case "DROP DATABASE":
                            sql.handleDropDatabase(line, keyword);
                            break;

                        case "INSERT INTO":
                            sql.handleInsertInto(line, keyword);
                            break;

                        case "VALUES":
                            sql.handleValues(line, keyword);
                            break;

                        case "DELETE FROM":
                            sql.handleDeleteFrom(line, keyword);
                            break;

                        case "FROM":
                            sql.handleFrom(line, keyword);
                            break;

                        case "UPDATE":
                            sql.handleUpdate(line, keyword);
                            break;

                        case "SET":
                            sql.handleSet(line, keyword);
                            break;

                        case "SELECT":
                            sql.handleSelect(line, keyword);
                            break;
                    }

                } catch (StringIndexOutOfBoundsException e) {
                    throw new StringIndexOutOfBoundsException("ERROR WHILE PARSING: " + e.getMessage());
                } catch (FileNotFoundException e) {
                    throw new FileNotFoundException("FILE NOT FOUND: " + e.getMessage());
                } catch (NoSuchFileException e) {
                    throw new NoSuchFileException("NOT A DATABASE: " + e.getMessage());
                } catch (FileSystemException e) {
                    throw new FileSystemException(e.getMessage());
                } catch (IOException e) {
                    throw new IOException(e.getMessage());
                } catch (Exception e){
                    throw new Exception("AN ERROR OCURRED WHILE EXECUTING COMMAND: " + e.getMessage());
                }
            }
        }

        if(!found){
            throw new IOException("NOT RECOGNIZABLE KEYWORDS");
        }
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public ArrayList<String> getPseudoKeywords() {
        return pseudoKeywords;
    }

    public void refreshDB(File file){
        this.database = new Database();
        this.database.setDbFile(file);
        this.database.retrieveTables();
    }
}
