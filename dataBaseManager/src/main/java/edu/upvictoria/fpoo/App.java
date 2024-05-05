/**
 * Perfect Support
 */

package edu.upvictoria.fpoo;

public class App {
    public static void main( String[] args ) {
        App app = new App();
        app.run();
    }

    public final Analyzer analyzer = new Analyzer();

    public void run(){
        StringBuffer inputLines;
        String[] lines;
        boolean recognizedStatement = false;

        while(true){
            System.out.print("$> ");
            inputLines = Reader.consoleReader(analyzer);
            if(inputLines == null) { continue; }

            lines = inputLines.toString().split("\n");
            for(String line : lines){
                recognizedStatement = analyzer.analyze(line);
                if(!recognizedStatement) { break; }
            }

            if(!recognizedStatement) { continue; }
        }
    }
}
