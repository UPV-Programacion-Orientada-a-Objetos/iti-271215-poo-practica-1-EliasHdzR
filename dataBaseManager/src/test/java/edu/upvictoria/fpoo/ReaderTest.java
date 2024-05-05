package edu.upvictoria.fpoo;

import junit.framework.TestCase;

public class ReaderTest extends TestCase {

    public final Analyzer analyzer = new Analyzer();

    public void testFormatInput() {
        StringBuffer input = new StringBuffer("SELECT * FROM ALUMNOS WHERE ID = 1;");
        StringBuffer expected = new StringBuffer("SELECT * \nFROM ALUMNOS \nWHERE ID = 1;");

        assertEquals(expected.toString(), Reader.formatInput(input, analyzer).toString());
    }

    public void testFormatInput_2() {
        StringBuffer input = new StringBuffer("CREATE TABLE;");
        StringBuffer expected = new StringBuffer("CREATE \nTABLE;");

        assertEquals(expected.toString(), Reader.formatInput(input, analyzer).toString());
    }
}