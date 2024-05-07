/**
 * Perfect Support
 */

package edu.upvictoria.fpoo;

import java.io.IOException;
import java.text.ParseException;

public class App {
    public static void main( String[] args ) {
        App app = new App();
        app.run();
    }

    public final Analyzer analyzer = new Analyzer();

    public void run(){
        StringBuffer inputLines;
        String[] lines;

        while(true){
            System.out.print("$> ");

            try{
                inputLines = Reader.consoleReader(analyzer);
            } catch (IOException e){
                System.out.println("ERR: Ocurrio un error al leer input de usuario: " + e.getMessage() + "\n");
                continue;
            } catch (ParseException e){
                System.out.println("ERR: Error de Sintaxis: " + e.getMessage() + e.getErrorOffset() + "\n");
                continue;
            }

            lines = inputLines.toString().split("\n");
            System.out.println(inputLines);

            try{
                for(String line : lines){
                    analyzer.analyzeSyntax(line);
                }
            } catch (IOException e){
                System.out.println("ERR: Sentencia no reconocida: " + e.getMessage() + "\n");
            }
        }
    }
}
