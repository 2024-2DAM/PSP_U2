package codigosClase.secretaria;

import java.util.concurrent.Semaphore;

public class Secretaria implements Runnable {
    //Atiende al alumnado de 5 en 5 como máximo
    //Secretaría llevará un contador de cuántos alumnos atiende
    private static Semaphore semaforo = new Semaphore(5);
    private static Object monitor = new Object();

    private String alumne;
    public static int contador = 0;

    public Secretaria(String alumne) {
        this.alumne = alumne;
    }

    public void setAlumne(String alumne) {
        this.alumne = alumne;
    }

    @Override
    public void run() {
        try {
            //Esto decrementaría en uno los permisos
            // del recurso compartido disponible
            semaforo.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Estoy atendiendo a " + alumne);
        esperar(1, 2);
        System.out.println("He terminado con " + alumne);
        //TODO esto hay que sincronizarloooo!
        synchronized (monitor) {
            contador++;
        }
        //Libera un permiso del recurso compartido
        semaforo.release();

    }

    private void esperar(int min, int max) {
        try {
            Thread.sleep((long) (Math.random() * (max - min) + min) * 1000);
            //Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
