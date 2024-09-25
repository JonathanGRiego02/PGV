package com.ejerciciosclase.pgv;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author Jonathan
 */
public class App {
     

    public static void main(String[] args) {
        try {
            Process proceso = new ProcessBuilder("Python", 
                "proceso_python.py").start();

            BufferedReader br = new BufferedReader(
                new InputStreamReader(proceso.getInputStream()));
            
            proceso.waitFor();
            int exitStatus = proceso.exitValue();
            System.out.println("Retorno: " + br.readLine());
            System.out.println("Valor de la salida: " + exitStatus);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
