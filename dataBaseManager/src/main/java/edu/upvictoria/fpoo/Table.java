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

public class Table {
    private final String tableName;
    private final File tableFile;
    ArrayList<ArrayList<Object>> data =  new ArrayList<>();

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

                ArrayList<Object> objectValues = new ArrayList<>(Arrays.asList(stringValues));
                this.data.add(objectValues);
            }
        } catch (IOException e){
            throw new FileSystemException("AN ERROR OCURRED WHILE OPENING FILE");
        }
    }

    public File getTableFile() {
        return tableFile;
    }

    public void printData(){
        for (ArrayList<Object> datum : data) {
            for (Object object : datum) {
                System.out.print(object + " | ");
            }
            System.out.println();
        }
    }

    public String getTableName() {
        return tableName;
    }
}
