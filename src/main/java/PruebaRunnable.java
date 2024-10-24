public class PruebaRunnable implements Runnable{

    private String id;

    public PruebaRunnable(String id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Hilo desde la interfaz Runnable");
        PruebaHilosMain.variable++;
        System.out.println("Hoy ponemos las fechas de los examenes");
    }
}
