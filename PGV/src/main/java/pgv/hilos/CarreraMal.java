package pgv.hilos;

// Clase principal para ejecutar la simulación de la carrera
public class CarreraMal {
    public static void main(String[] args) {
        // Crear instancias de corredores con diferentes velocidades
        CorredorMal corredor1 = new CorredorMal("Usain Bolt", 50); // Velocidad rápida
        CorredorMal corredor2 = new CorredorMal("Carl Lewis", 70); // Velocidad media
        CorredorMal corredor3 = new CorredorMal("Jesse Owens", 100); // Velocidad lenta

        // Crear hilos para cada corredor
        Thread hilo1 = new Thread(corredor1);
        Thread hilo2 = new Thread(corredor2);
        Thread hilo3 = new Thread(corredor3);

        // Iniciar la carrera
        System.out.println("¡La carrera comienza!");
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}

// Clase Corredor que implementa Runnable
class CorredorMal implements Runnable {
    private final String nombre;
    private final int velocidad; // Tiempo en milisegundos para avanzar un "metro"
    private static final int DISTANCIA_TOTAL = 100; // Distancia total de la carrera en metros
    private static int posicion = 1; // Posición compartida para mostrar el orden de llegada

    public CorredorMal(String nombre, int velocidad) {
        this.nombre = nombre;
        this.velocidad = velocidad;
    }

    @Override
    public void run() {
        int distanciaRecorrida = 0;

        while (distanciaRecorrida < DISTANCIA_TOTAL) {
            try {
                // El corredor avanza un metro y duerme según su velocidad
                Thread.sleep(velocidad);
                distanciaRecorrida++;
                System.out.println(nombre + " ha avanzado " + distanciaRecorrida + " metros.");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Cuando termina la carrera, muestra la posicion de llegada
        System.out.println(nombre + " ha terminado la carrera en la posicion " + obtenerPosicion() + "!");
    }

    // Método sincronizado para asegurar que la posicion se actualice correctamente
    private synchronized int obtenerPosicion() {
        return posicion++;
    }
}
