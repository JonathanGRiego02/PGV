package pgv.hilos;

public class CorredorBien {

    public static void main(String[] args) {
        // Crear e iniciar la carrera con 5 corredores
        int numeroDeCorredores = 5;
        Carrera carrera = new Carrera(numeroDeCorredores);
        carrera.iniciarCarrera();
    }
}

class Carrera {
    private int numeroDeCorredores;
    private Thread[] corredoresThreads;

    public Carrera(int numeroDeCorredores) {
        this.numeroDeCorredores = numeroDeCorredores;
        corredoresThreads = new Thread[numeroDeCorredores];
    }

    // Método para iniciar la carrera
    public void iniciarCarrera() {
        System.out.println("¡La carrera ha comenzado!");
        for (int i = 0; i < numeroDeCorredores; i++) {
            Corredor corredor = new Corredor("Corredor " + (i + 1));
            corredoresThreads[i] = new Thread(corredor);
            corredoresThreads[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (Thread t : corredoresThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("¡La carrera ha terminado!");
    }
}

class Corredor implements Runnable {
    private static final int META = 100; // Metros totales de la carrera
    private String nombre;
    private int posicionActual = 0;
    private static String ganador = null;

    public Corredor(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while (posicionActual < META && ganador == null) {
            // El corredor avanza una distancia aleatoria en cada paso
            int distancia = (int) (Math.random() * 10) + 1;
            posicionActual += distancia;

            // Imprimir el avance del corredor
            System.out.println(nombre + " ha avanzado a la posición " + posicionActual + " metros.");

            // Simular tiempo de descanso entre pasos
            try {
                Thread.sleep((int) (Math.random() * 500) + 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Si el corredor alcanza o supera la meta y no hay ganador aún, se declara como ganador
            if (posicionActual >= META && ganador == null) {
                ganador = nombre;
                System.out.println("¡" + nombre + " ha ganado la carrera!");
            }
        }
    }
}