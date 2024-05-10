/**
 * Perfect Support
 */

package edu.upvictoria.fpoo;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class App {
    public static void main( String[] args ) {
        App app = new App();
        app.run();
    }

    public final Analyzer analyzer = new Analyzer();
    public final Reader reader = new Reader();

    public void run(){
        ArrayList<String> lines;

        while(true){
            System.out.print("$> ");
            try{
                lines = reader.consoleReader(analyzer);
            } catch (IOException e){
                System.out.println("ERR: Ocurrio un error al leer input de usuario: " + e.getMessage() + "\n");
                continue;
            } catch (ParseException e){
                System.out.println("ERR: Error de Sintaxis: " + e.getMessage() + e.getErrorOffset() + "\n");
                continue;
            }

            try{
                for(String line : lines){
                    analyzer.analyzeSyntax(line, lines.size());
                }
            } catch (IOException e){
                System.out.println("ERR: Error en la Sentencia: " + e.getMessage() + "\n");
            } catch (Exception e){
                System.out.println("ERR: " + e.getMessage() + "\n");
            }
        }
    }
}
