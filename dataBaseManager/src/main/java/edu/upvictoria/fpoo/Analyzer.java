package edu.upvictoria.fpoo;

import java.io.IOException;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.Collection;
import java.io.File;

public class Analyzer {
    private final HashMap<String, Consumer<String>> keywords = new HashMap<>();
    private  final HashMap<String, Consumer<String>> pseudoKeywords = new HashMap<>();
    private final String appRoute = new File("").getAbsolutePath() + "\\";

    public Analyzer(){
        keywords.put("USE", this::handleUse);
        keywords.put("SHOW TABLES", this::handleShow);
        keywords.put("TABLE", this::handleTable);
        keywords.put("DATABASE", this::handleDatabase);
        keywords.put("CREATE", this::handleCreate);
        keywords.put("DROP", this::handleDrop);
        keywords.put("INSERT", this::handleInsert);
        keywords.put("INTO", this::handleInto);
        keywords.put("VALUES", this::handleValues);
        keywords.put("DELETE", this::handleDelete);
        keywords.put("FROM", this::handleFrom);
        keywords.put("WHERE", this::handleWhere);
        keywords.put("UPDATE", this::handleUpdate);
        keywords.put("SET", this::handleSet);
        keywords.put("SELECT", this::handleSelect);
        keywords.put("ORDER BY", this::handleOrder);
        keywords.put("GROUP BY", this::handleGroup);
        keywords.put("LIMIT", this::handleLimit);

        pseudoKeywords.put("AND", this::handleAnd);
        pseudoKeywords.put("OR", this::handleOr);
        pseudoKeywords.put("NOT", this::handleNot);
        pseudoKeywords.put("AS", this::handleAs);
        pseudoKeywords.put("NULL", this::handleNull);
        pseudoKeywords.put("PRIMARY KEY", this::handlePrimary);
        pseudoKeywords.put("NUMBER", this::handleNumber);
        pseudoKeywords.put("VARCHAR", this::handleVarchar);
        pseudoKeywords.put("CHAR", this::handleChar);
        pseudoKeywords.put("BOOLEAN", this::handleBoolean);
        pseudoKeywords.put("DATE", this::handleDate);
        pseudoKeywords.put("IN", this::handleIn);
    }

    public void analyzeSyntax(String line) throws IOException {
        boolean found = false;
        System.out.println("linea a analizar:" + line);

        for(String keyword : keywords.keySet()){
            if(line.contains(keyword)){
                found = true;
                keywords.get(keyword).accept(line);
            }
        }

        if(!found){
            throw new IOException("NOT RECOGNIZABLE KEYWORDS");
        }
    }

    public String clean(String line, String keyword) throws StringIndexOutOfBoundsException {
        int endOfKeyword = line.indexOf(keyword) + keyword.length();
        int semicolon = line.indexOf(";");

        try {
            line = line.substring(endOfKeyword + 1, semicolon);
        } catch (StringIndexOutOfBoundsException e) {
            throw new StringIndexOutOfBoundsException("UNEXPECTED ERROR OCURRED");
        }

        System.out.println("linea limpia: " + line);
        return line;
    }

    public void handleUse(String line){
        try {
            String givenPath = clean(line,"USE");
        } catch (StringIndexOutOfBoundsException e){
            //namas pa que no ande molestando
        }


    }

    public void handleShow(String line){
    }

    public void handleTable(String line){
    }

    public void handleDatabase(String line){
    }

    public void handleCreate(String line){
    }

    public void handleDrop(String line){
    }

    public void handleInsert(String line){
    }

    public void handleInto(String line){
    }

    public void handleValues(String line){
    }

    public void handleDelete(String line){
    }

    public void handleFrom(String line){
    }

    public void handleWhere(String line){
    }

    public void handleAnd(String line){
    }

    public void handleOr(String line){
    }

    public void handleNot(String line){
    }

    public void handleUpdate(String line){
    }

    public void handleSet(String line){
    }

    public void handleSelect(String line){
    }

    public void handleAs(String line){
    }

    public void handleOrder(String line){
    }

    public void handleGroup(String line){
    }

    public void handleLimit(String line){
    }

    public void handleNull(String line){
    }

    public void handlePrimary(String line){
    }

    public void handleNumber(String line){
    }

    public void handleVarchar(String line){
    }

    public void handleChar(String line){
    }

    public void handleBoolean(String line){
    }

    public void handleDate(String line){
    }

    public void handleIn(String line){
    }

    public Collection<String> getKeywords() {
        return keywords.keySet();
    }
}
