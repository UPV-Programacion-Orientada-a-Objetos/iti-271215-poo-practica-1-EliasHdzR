package edu.upvictoria.fpoo;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.Collection;
import java.io.File;

public class Analyzer {
    private final HashMap<String, Consumer<String>> keywords = new HashMap<>();
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
        keywords.put("AND", this::handleAnd);
        keywords.put("OR", this::handleOr);
        keywords.put("NOT", this::handleNot);
        keywords.put("UPDATE", this::handleUpdate);
        keywords.put("SET", this::handleSet);
        keywords.put("SELECT", this::handleSelect);
        keywords.put("AS", this::handleAs);
        keywords.put("ORDER BY", this::handleOrder);
        keywords.put("GROUP BY", this::handleGroup);
        keywords.put("LIMIT", this::handleLimit);
        keywords.put("NULL", this::handleNull);
        keywords.put("PRIMARY KEY", this::handlePrimary);
        keywords.put("NUMBER", this::handleNumber);
        keywords.put("VARCHAR", this::handleVarchar);
        keywords.put("CHAR", this::handleChar);
        keywords.put("BOOLEAN", this::handleBoolean);
        keywords.put("DATE", this::handleDate);
        keywords.put("IN", this::handleIn);
    }

    public boolean analyze(String line){
        for(String keyword : keywords.keySet()){
            if(line.contains(keyword)){
                keywords.get(keyword).accept(line);
                return true;
            }
        }

        System.out.println("ERR: Sentencia no Reconocida\n");
        return false;
    }

    public String clean(String line){
        int endOfKeyword = line.indexOf(" ");
        int semicolon = line.indexOf(";");
        line = line.substring(endOfKeyword + 1, semicolon);

        if(line.contains(";")){
            return null;
        }

        return line;
    }

    public void handleUse(String line){
        String givenPath = clean(line);

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
