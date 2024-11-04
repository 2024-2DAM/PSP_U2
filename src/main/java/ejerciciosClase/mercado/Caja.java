package ejerciciosClase.mercado;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;

public class Caja extends Thread {
    private String nombre;

    //Estos dos atributos es solamente para tener acceso a ellos en run()
    // porque si no, desde run no tengo forma de acceder
    private String nombreCliente;
    private int numProductos;

    //Monitor para garantizar exclusión mutua (ej 1.2)
    private static Object monitor = new Object();

    //Para el ejercicio 1.3: tengo 3 cajas:
    private static Semaphore sem = new Semaphore(3);

    public Caja(String nombre, String nombreCliente, int numProductos) {
        this.nombre = nombre;
        this.nombreCliente = nombreCliente;
        this.numProductos = numProductos;
    }

    @Override
    public void run() {
        //El run no puede recibir ningún parámetro
        //Así que para meterle otra información (nombreCliente y numProductos)
        // tienen que estar en otro sitio -->
        //this.procesarCompra(XXXXXXXX, XXXXXXX);
        this.procesarCompra(nombreCliente, numProductos);

    }

    public void procesarCompra(String nombreCliente, int numProductos) {
        //synchronized (monitor) { Ejercicio 1.2
        try {
            sem.acquire();  //1.3 Cojo uno de los recursos compartidos gestionados por el semáforo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("La caja " + nombre + " recibe a " + nombreCliente + " con " + numProductos + " productos");
        for (int i = 0; i < numProductos; i++) {
            try {
                Thread.sleep((long) (Math.random() * 10 * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Caja " + nombre + " - Cliente: " + nombreCliente + " - Producto: " + i);
        }
        // } Esto es del ejercicio 1.2

        //Ejercicio 1.4:
        File file = new File("./files/ventas.txt");
        synchronized (file) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
                LocalDateTime d = LocalDateTime.now();
                //año-mes-dia_hora-minutos\tNombre\tNumProductos
                DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
                pw.println(d.format(f) + "\t" + nombreCliente + "\t" + numProductos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("La caja " + nombre + " termina");
        sem.release();  //1.3 -> libero uno de los recursos compartidos
    }
}
