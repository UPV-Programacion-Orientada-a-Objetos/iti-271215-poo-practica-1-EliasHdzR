package edu.upvictoria.fpoo;

import junit.framework.TestCase;

import java.util.ArrayList;

public class SQLTest extends TestCase {
    SQL sql = new SQL();

    public void testClean_1() {
        String line = "UPDATE FROM ALUMNOS WHERE ID = 1;";
        String keyword = "UPDATE FROM";
        String expected = "ALUMNOS WHERE ID = 1";

        assertEquals(expected,sql.clean(line,keyword));
    }

    public void testClean_2() {
        String line = "INSERT INTO ALUMNOS (MATRICULA, NOMBRE) VALUES (2230200,ELIAS);";
        String keyword = "INSERT INTO";
        String expected = "ALUMNOS (MATRICULA, NOMBRE) VALUES (2230200,ELIAS)";

        assertEquals(expected,sql.clean(line,keyword));
    }

    public void testClean_3(){
        String line = "CREATE TABLE ALUMNOS(MATRICULA INT, NOMBRE VARCHAR, APELLIDOS VARCHAR);";
        String keyword = "CREATE TABLE";
        String expected = "ALUMNOS(MATRICULA INT, NOMBRE VARCHAR, APELLIDOS VARCHAR)";

        assertEquals(expected,sql.clean(line,keyword));
    }

    public void testClean_4(){
        String line = "CREATE DATABASE ALUMNOS;";
        String keyword = "CREATE DATABASE";
        String expected = "ALUMNOS";

        assertEquals(expected,sql.clean(line,keyword));
    }

    public void testClean_5(){
        String line = "DROP TABLE ALUMNOS;";
        String keyword = "DROP TABLE";
        String expected = "ALUMNOS";

        assertEquals(expected,sql.clean(line,keyword));
    }

    public void testSplitValues_1() throws Exception {
        String line = "ALUMNOS(MATRICULA INT,NOMBRE VARCHAR,APELLIDOS VARCHAR)";
        ArrayList<String> expected = new ArrayList<>();
        expected.add("ALUMNOS");
        expected.add("MATRICULA");
        expected.add("NOMBRE");
        expected.add("APELLIDOS");

        assertEquals(expected, sql.splitValues(line));
    }

    public void testSplitValues_2() throws Exception {
        String line = "ALUMNOS (MATRICULA INT,NOMBRE VARCHAR,APELLIDOS VARCHAR)";
        ArrayList<String> expected = new ArrayList<>();
        expected.add("ALUMNOS");
        expected.add("MATRICULA");
        expected.add("NOMBRE");
        expected.add("APELLIDOS");

        assertEquals(expected, sql.splitValues(line));
    }

    public void testSplitValues_3() throws Exception {
        String line = "ALUMNOS (\nMATRICULA INT,\nNOMBRE VARCHAR,\nAPELLIDOS VARCHAR\n)";
        ArrayList<String> expected = new ArrayList<>();
        expected.add("ALUMNOS");
        expected.add("MATRICULA");
        expected.add("NOMBRE");
        expected.add("APELLIDOS");

        assertEquals(expected, sql.splitValues(line));
    }

    public void testSplitValues_4() throws Exception {
        String line = "ALUMNOS (\n\tMATRICULA INT,\n\tNOMBRE VARCHAR,\n\tAPELLIDOS VARCHAR\n)";
        ArrayList<String> expected = new ArrayList<>();
        expected.add("ALUMNOS");
        expected.add("MATRICULA");
        expected.add("NOMBRE");
        expected.add("APELLIDOS");

        assertEquals(expected, sql.splitValues(line));
    }

    public void testSplitInsertionColumns_1() {
    }
}