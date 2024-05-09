package edu.upvictoria.fpoo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.io.File;

public class Analyzer {
    private final ArrayList<String> keywords = new ArrayList<>();
    private final SQL sql = new SQL();
    private Database database = new Database();
    //private final ArrayList<String> pseudoKeywords = new ArrayList<>();

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

        /*pseudoKeywords.add("AND");
        pseudoKeywords.add("OR");
        pseudoKeywords.add("NOT");
        pseudoKeywords.add("AS");
        pseudoKeywords.add("NULL");
        pseudoKeywords.add("PRIMARY KEY");
        pseudoKeywords.add("NUMBER");
        pseudoKeywords.add("VARCHAR");
        pseudoKeywords.add("CHAR");
        pseudoKeywords.add("BOOLEAN");
        pseudoKeywords.add("DATE");
        pseudoKeywords.add("IN");*/
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

                        case "CREATE TABLE":
                            sql.handleCreateTable(line, keyword);
                            break;

                        case "CREATE DATABASE":
                            if(totalLines > 1){
                                throw new IOException("SYNTAX ERROR");
                            }

                            sql.handleCreateDatabase(line, keyword);
                            break;

                        case "DROP TABLE":
                            sql.handleDropTable(line, keyword);
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
                    throw new StringIndexOutOfBoundsException("ERROR WHILE PARSING");
                } catch (FileNotFoundException e) {
                    throw new FileNotFoundException("FILE NOT FOUND");
                } catch (NoSuchFileException e) {
                    throw new NoSuchFileException("NOT A DATABASE");
                } catch (FileSystemException e) {
                    throw new FileSystemException(e.getMessage());
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

    public void refreshDB(File file){
        this.database = new Database();
        this.database.setDbFile(file);
        this.database.retrieveTables();
    }
}
