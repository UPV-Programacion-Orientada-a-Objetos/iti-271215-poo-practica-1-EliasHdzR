package edu.upvictoria.fpoo;

import junit.framework.TestCase;

public class ReaderTest extends TestCase {

    public final Analyzer analyzer = new Analyzer();
    public final Reader reader = new Reader();

    public void testFormatInput_2() {
        StringBuffer input = new StringBuffer("CREATE TABLE;");

        assertEquals("CREATE TABLE;", reader.formatInput(input, analyzer).toString());
    }
}