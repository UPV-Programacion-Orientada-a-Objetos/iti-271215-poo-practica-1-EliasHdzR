package edu.upvictoria.fpoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {
    public static StringBuffer consoleReader(Analyzer analyzer){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try{
            String input;
            StringBuffer inputLines = new StringBuffer();

            while ((input = reader.readLine()) != null) {
                if(input.trim().isEmpty()){
                    continue;
                }
                inputLines.append(input.toUpperCase().trim());
                if(input.endsWith(";")) {
                    break;
                }
                inputLines.append("\n");
            }

            return Reader.formatInput(inputLines, analyzer);

        } catch (IOException e) {
            System.out.println("ERR: Ocurrió un error al leer entrada de usuario " + e.getMessage() + "\n");
            return  null;
        }
    }

    public static StringBuffer formatInput(StringBuffer input, Analyzer analyzer){
        for(String keyword : analyzer.getKeywords()){
            int index = input.indexOf(keyword);
            if(index != -1){
                input.insert(index,"\n");
            }
        }

        input.delete(0,1);
        return input;
    }
}
