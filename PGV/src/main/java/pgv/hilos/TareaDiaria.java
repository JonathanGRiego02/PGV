package pgv.hilos;

import java.util.ArrayList;
import java.util.Scanner;

public class TareaDiaria implements Runnable{
    private final String nombre_tarea;
    private int progreso_tarea = 0;

    public TareaDiaria(String _nombre_tarea) {
        nombre_tarea = _nombre_tarea;
    }

    @Override
    public void run() {
        System.out.println(nombre_tarea + " ha comenzado.");

        while (progreso_tarea <= 5) {
            System.out.println(nombre_tarea + " - Progreso " + progreso_tarea + "/5");
            progreso_tarea++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(nombre_tarea + " ha terminado.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Array con todas las tareas
        ArrayList<String> tareas = new ArrayList<>();
        tareas.add("Preparar desayuno");
        tareas.add("Hacer ejercicio");
        tareas.add("Leer un libro");

        ArrayList<Thread> tareasThread = new ArrayList<>();
        String opcion = "";
        String opcion_ejecutar = Integer.toString(tareas.size() + 1);

        do  {
            System.out.println("Elige una tarea: ");
            for(int i = 0; i < tareas.size(); i++) {
                System.out.println((i + 1) + ". " + tareas.get(i));
            }
            System.out.println((tareas.size() + 1) + ". Ejecutar tareas");
            opcion = scanner.nextLine();

            if (!opcion.equals(opcion_ejecutar)) {
                int indice = Integer.parseInt(opcion) - 1;

                // Creamos el hilo con la tarea antes de eliminarla
                TareaDiaria nueva_tarea = new TareaDiaria(tareas.get(indice));
                Thread tarea = new Thread(nueva_tarea);
                tareasThread.add(tarea);

                // Eliminamos la tarea después de crear el hilo
                tareas.remove(indice);
            }

            // Actualizamos la opción de ejecutar tareas
            opcion_ejecutar = Integer.toString(tareas.size() + 1);

        } while (!opcion.equals(opcion_ejecutar));

        // Ejecutamos todos los hilos
        for (Thread t : tareasThread) {
            t.start();
        }

        scanner.close();
    }
}
