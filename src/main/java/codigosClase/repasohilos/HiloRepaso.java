package codigosClase.repasohilos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Semaphore;

public class HiloRepaso extends Thread {
    private String id;
    public static int contador = 0;
    private boolean aumentar;
    private static Object monitorContador = new Object();
    private static Object monitorFile = new Object();
    private static Semaphore sem = new Semaphore(10);

    public HiloRepaso(String id, boolean aumentar) {
        this.id = id;
        this.aumentar = aumentar;
    }

    @Override
    public void run() {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int cadena = id.length();
        //System.out.println("El id tiene una longitud de " + cadena);
        //System.out.println("El doble de esa longitud es " + cadena * 2);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.print(id + " ");
        if (this.aumentar) {
            aumentar();
        } else {
            disminuir();
        }
        //Ahora escriben en un fichero:
        synchronized (monitorFile) {
            try (PrintWriter pw = new PrintWriter(new FileWriter("a.txt", true), true)) {
                pw.println("Hilo " + id);
                pw.println("Aumentar: " + aumentar);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sem.release();
    }

    public void aumentar() {
        //System.out.print("+");
        synchronized (monitorContador) {
            HiloRepaso.contador++;
        }
    }

    public void disminuir() {
        //System.out.print("-");

        synchronized (monitorContador) {
            HiloRepaso.contador--;
        }
    }
}
