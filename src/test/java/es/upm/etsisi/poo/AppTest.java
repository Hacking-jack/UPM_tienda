package es.upm.etsisi.poo;

import es.upm.etsisi.poo.view.App;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    public void testSalidaIgualAArchivo() throws IOException {
        // Capturar la salida estándar
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            // Ejecutar tu programa con el parámetro deseado
            App.main(new String[]{"enunciados/inputE3"});
        } finally {
            // Restaurar stdout
            System.setOut(originalOut);
        }

        // Leer archivo esperado
        File expectedFile = new File("enunciados/outputE3");
        byte[] bytes = Files.readAllBytes(expectedFile.toPath());
        String expectedOutput = new String(bytes, StandardCharsets.UTF_8);

        // Salida real del programa
        String actualOutput = outContent.toString((StandardCharsets.UTF_8));

        // Comparar
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    /*public void testSalidaIgualAArchivo2() throws IOException {
        // Capturar la salida estándar
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            // Ejecutar tu programa con el parámetro deseado
            App.main(new String[]{"enunciados/input PruebasE2"});
        } finally {
            // Restaurar stdout
            System.setOut(originalOut);
        }

        // Leer archivo esperado
        File expectedFile = new File("enunciados/PruebasE2");
        byte[] bytes = Files.readAllBytes(expectedFile.toPath());
        String expectedOutput = new String(bytes, StandardCharsets.UTF_8);

        // Salida real del programa
        String actualOutput = new String(outContent.toByteArray(), (StandardCharsets.UTF_8));

        // Comparar
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }*/

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }
}
