package codigosClase.hilosFicheros;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EscribeFichero extends Thread {
    //Multihilo

    //Atributos
    //variable estática compartida
    private static int valor = 0;
    //boolean incrementar/decrementar
    private boolean incrementa;
    private String nombre;
    private static Object monitor = new Object();
    private static File file = new File("./files/haloween.txt");

    public EscribeFichero(boolean incrementa) {
        this.incrementa = incrementa;
    }

    public static int getValor() {
        return valor;
    }

    //métodos (run)
    //incrementar o decrementar la variable
    //escribe en el fichero un timestamp (31-10-2024-12:02:51) y el valor de la variable.

    @Override
    public void run() {
        //Sí necesito sincronizar (recurso compartido)
        synchronized (monitor) {
            if (incrementa) {
                valor++;
            } else {
                valor--;
            }
        }

        //Esto no necesito sincronizarlo, porque no es un recurso compartido
        nombre = "Juan";
        System.out.print(". ");

        //Tengo que sincronizarlo porque es un recurso compartido (no puede haber varios
        // hilos escribiendo a la vez en el mismo fichero.

        synchronized (file) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(file, true));) {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss");
                pw.println(now.format(formatter) + " " + valor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
