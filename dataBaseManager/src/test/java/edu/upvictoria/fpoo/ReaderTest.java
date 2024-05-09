package edu.upvictoria.fpoo;

import junit.framework.TestCase;

public class ReaderTest extends TestCase {

    public final Analyzer analyzer = new Analyzer();
    public final Reader reader = new Reader();

    public void testFormatInput() {
        StringBuffer input = new StringBuffer("SELECT * FROM ALUMNOS WHERE ID = 1;");

        assertEquals("SELECT * \nFROM ALUMNOS \nWHERE ID = 1;", reader.formatInput(input, analyzer).toString());
    }

    public void testFormatInput_2() {
        StringBuffer input = new StringBuffer("CREATE TABLE;");

        assertEquals("CREATE \nTABLE;", reader.formatInput(input, analyzer).toString());
    }
}