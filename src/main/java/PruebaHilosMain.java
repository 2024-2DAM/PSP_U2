public class PruebaHilosMain {
    private static PruebaThread pt;

    public static void main(String[] args) {
        System.out.println("INICIO");
        /*PruebaThread pt = new PruebaThread("hilo1");
        pt.start(); //Con start planifico el hilo para que se ejecute cuando le toque
        */

        int prioridad = Thread.MIN_PRIORITY;    //1
        for (int i = 0; i < 10; i++) {
            PruebaThread hilo = new PruebaThread("hilo" + i);
            hilo.setPriority(prioridad);
            hilo.start();
            if (prioridad == PruebaThread.MAX_PRIORITY) {
                prioridad = Thread.MIN_PRIORITY;    //1
            } else {
                prioridad++;
            }
        }

        System.out.println("Termina el principal");
    }
}
