package pgv.hilos.wok;
import java.util.ArrayList;
import java.util.Random;


public class WokConHilos  implements Runnable {

    private static ArrayList<String> pedidos = new ArrayList<>();
    private final Cocinero cocinero; // Cocinero que ejecutará el hilo
    private static final Random random = new Random(); // Generador de números aleatorios


    public WokConHilos(Cocinero cocinero) {
        this.cocinero = cocinero;
    }

    // Método que simula cocinar un plato
    private void cocinar(String pedido) {
        System.out.println(cocinero.getNombre() + " está preparando: " + pedido);
        try {
            Thread.sleep(2000 + random.nextInt(2001)); // Simular tiempo de cocinado con un retraso de 2 segundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(cocinero.getNombre() + " ha sido interrumpido.");
        }
        System.out.println(cocinero.getNombre() + " ha terminado de preparar: " + pedido);
    }

    @Override
    public void run() {
        while (true) {
            String pedido;
            // Bloque sincronizado para acceder a la lista de pedidos de manera segura
            synchronized (pedidos) {
                if (pedidos.isEmpty()) {
                    // Si ya no quedan pedidos, salir del bucle
                    System.out.println(cocinero.getNombre() + " ha terminado todos los pedidos.");
                    break;
                }
                // Tomar el primer pedido de la lista y quitarlo
                pedido = pedidos.remove(0);
            }
            // Cocinar el pedido fuera del bloque sincronizado para no bloquear a otros cocineros
            cocinar(pedido);
        }
    }

    public static void main(String[] args ) {
        // Cocineros
        Cocinero pepe = new Cocinero("Pepe");
        Cocinero estefania = new Cocinero("Estefania");
        Cocinero juan = new Cocinero("Juan");

        // Platos
        pedidos.add("Arroz 3 delicias");
        pedidos.add("Tallarines con pollo");
        pedidos.add("Pollo al limón");
        pedidos.add("Ternera con almendras");
        pedidos.add("Cerdo agridulce");
        pedidos.add("Pato a la naranja");

        // Iniciar los hilos
        Thread hiloPepe = new Thread(new WokConHilos(pepe));
        Thread hiloEstefania = new Thread(new WokConHilos(estefania));
        Thread hiloJuan = new Thread(new WokConHilos(juan));

        hiloPepe.start();
        hiloEstefania.start();
        hiloJuan.start();

        // Esperar a que todos los hilos terminen
        try {
            hiloPepe.join();
            hiloEstefania.join();
            hiloJuan.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
