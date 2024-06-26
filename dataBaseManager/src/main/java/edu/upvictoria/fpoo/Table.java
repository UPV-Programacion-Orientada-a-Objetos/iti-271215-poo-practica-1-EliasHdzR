package edu.upvictoria.fpoo;

// OBJECT IS THE WAY

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Table {
    private final String tableName;
    private final File tableFile;
    ArrayList<ArrayList<String>> data =  new ArrayList<>();

    //para crear nueva tabla
    public Table(File tableFile, ArrayList<String> data){
        this.tableFile = tableFile;
        this.data.add(data);
        int i = tableFile.getName().indexOf('.');
        this.tableName = tableFile.getName().substring(0,i);
    }

    //para recuperar tabla ya existente
    public Table(File tableFile) throws FileSystemException {
        this.tableFile = tableFile;
        int i = tableFile.getName().indexOf('.');
        this.tableName = tableFile.getName().substring(0,i);

        Charset charset = StandardCharsets.UTF_8;

        try {
            BufferedReader br = new BufferedReader(new FileReader(tableFile, charset));
            String line;

            while((line = br.readLine()) != null){
                String[] stringValues = line.split(",");

                ArrayList<String> objectValues = new ArrayList<>(Arrays.asList(stringValues));
                this.data.add(objectValues);
            }
        } catch (IOException e){
            throw new FileSystemException("AN ERROR OCURRED WHILE OPENING FILE");
        }
    }

    public File getTableFile() {
        return tableFile;
    }

    public String getTableName() {
        return tableName;
    }

    public void appendData(ArrayList<String> rows) throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        StringBuilder line = new StringBuilder();

        try{
            FileWriter fw = new FileWriter(this.tableFile, charset, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);

            for(int i = 0; i < rows.size(); i++){
                line.append(rows.get(i));

                if(i != (rows.size() - 1)){
                    line.append(",");
                }
            }

            out.println(line);
            out.flush();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    public void printData(){
        for (ArrayList<String> datum : data) {
            for (String object : datum) {
                System.out.print("| " + object + "\t");
            }
            System.out.println("|");
            for (int i = 0; i < datum.size(); i++) {
                System.out.print("+------------");
            }
            System.out.println("+");
        }
    }

    public void printData(ArrayList<String> columns) {
        ArrayList<Integer> columnIndexes = new ArrayList<>();
        for(String column : columns){
            int i = data.get(0).indexOf(column);
            if (i != -1) {
                columnIndexes.add(i);
            }
        }

        for(ArrayList<String> datum : data){
            for(int columnIndex : columnIndexes){
                System.out.print(datum.get(columnIndex) + " | ");
            }
            System.out.println();
        }
    }

    public ArrayList<String> getColumnsName(){
        return data.get(0);
    }
}
