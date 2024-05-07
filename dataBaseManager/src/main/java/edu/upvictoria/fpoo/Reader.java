package edu.upvictoria.fpoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class Reader {
    public static StringBuffer consoleReader(Analyzer analyzer) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try{
            String input;
            StringBuffer inputLines = new StringBuffer();

            while ((input = reader.readLine()) != null) {
                if(input.trim().isEmpty()){
                    continue;
                }

                inputLines.append(input.toUpperCase().trim());

                int index = input.indexOf(";");
                int index2 = input.indexOf(";",index + 1);
                if(index2 != -1){
                    throw new ParseException("SYNTAX ERROR AT COLUMN: ", index);
                }

                if(input.endsWith(";")) {
                    break;
                }
            }

            return Reader.formatInput(inputLines, analyzer);

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    public static StringBuffer formatInput(StringBuffer input, Analyzer analyzer){
        int lastIndex = 0;
        boolean firstKeyWord = true;

        for(String keyword : analyzer.getKeywords()) {
            int index = input.indexOf(keyword, lastIndex);

            if(index != -1){
                input.insert(index,"\n");
                lastIndex =  index + keyword.length() + 1;
            }
        }

        if(input.indexOf("\n") < 3){
            input.deleteCharAt(input.indexOf("\n"));
        }

        return input;
    }
}
