package codigosClase.repasohilos;

public class HiloRunnableMain {
    public static void main(String[] args) {
        HiloRunnable h = new HiloRunnable();
        //h.start();
        Thread t = new Thread(h);
        t.start();
        h.run();
    }
}
