package pgv.hilos.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintQueueSystem {

    public static void main(String[] args) {
        ExecutorService printer = Executors.newSingleThreadExecutor();
        printer.execute(() -> {
            System.out.println("Imprimiendo documento 1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Documento 1 impreso");
        });
        printer.execute(() -> {
            System.out.println("Imprimiendo documento 2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Documento 2 impreso");
        });
        printer.shutdown();
    }
}





