package codigosClase;

public class PruebaRunnable implements Runnable{

    private String id;
    private static Object monitor = new Object();

    public PruebaRunnable(String id) {
        this.id = id;
    }

    @Override
    public void run() {

        //Tengo que identificar las líneas de código que me pueden ocasionar la inconsistencia de memoria
        synchronized (monitor) {
            PruebaHilosMain.variable++;
        }
        //Simulo que tiene que hacer operaciones y en total tardan en ejecutarse entre 0 y 1 segundo
        //Esta parte es independiente entre todos los hilos y sí se puede ejecutar en paralelo
        try {
            Thread.sleep((long) (Math.random()*1000));
            System.out.print(". ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
