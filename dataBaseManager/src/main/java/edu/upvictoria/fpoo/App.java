/**
 * Perfect Support
 */

package edu.upvictoria.fpoo;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class App {
    public static void main( String[] args ) {
        App app = new App();
        app.run();
    }

    public final Analyzer analyzer = new Analyzer();
    public final Reader reader = new Reader();

    public void run(){
        StringBuffer inputLines;
        String[] lines;

        while(true){
            System.out.print("$> ");
            try{
                inputLines = reader.consoleReader(analyzer);
            } catch (IOException e){
                System.out.println("ERR: Ocurrio un error al leer input de usuario: " + e.getMessage() + "\n");
                continue;
            } catch (ParseException e){
                System.out.println("ERR: Error de Sintaxis: " + e.getMessage() + e.getErrorOffset() + "\n");
                continue;
            }

            lines = inputLines.toString().split("\n");

            try{
                Object object = new Object();
                for(String line : lines){
                    object = analyzer.analyzeSyntax(line, object);
                }
            } catch (IOException e){
                System.out.println("ERR: Sentencia no reconocida: " + e.getMessage() + "\n");
            } catch (Exception e){
                System.out.println("ERR: " + e.getMessage() + "\n");
            }
        }
    }
}
