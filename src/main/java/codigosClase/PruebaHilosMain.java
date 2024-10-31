package codigosClase;

public class PruebaHilosMain {
    private static PruebaThread pt;
    public static int variable = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("INICIO");

        /*codigosClase.PruebaThread pt = new codigosClase.PruebaThread("hilo1");
        pt.start(); //Con start planifico el hilo para que se ejecute cuando le toque
        */

        int prioridad = Thread.MIN_PRIORITY;    //1
        for (int i = 0; i < 10; i++) {
            PruebaThread hilo = new PruebaThread("hilo" + i);
            //hilo.setPriority(prioridad);
           // hilo.start();
//            if (prioridad == codigosClase.PruebaThread.MAX_PRIORITY) {
//                prioridad = Thread.MIN_PRIORITY;    //1
//            } else {
//                prioridad++;
//            }
            //join espera a que termine el hilo:
            //hilo.join();

        }

        //Thread.sleep(10000);

        PruebaThread hilo = new PruebaThread("hilo1");
        hilo.start();
        //hilo.join();
        // hilo.start();  //No se puede: no puedo llamar al método start dos veces del mismo objeto

        PruebaRunnable pr = new PruebaRunnable("r1");
        //pr.start();   //No lo tengo, lo tengo que envolver en un obj de la clase Thread
        int numHilos = 100000;
        //En este array voy a guardar todos los hilos que lanzo, para luego ser capaz de esperarlos (join)
        Thread[] hilos = new Thread[numHilos];

        for (int i = 0; i < numHilos; i++) {
            Thread hiloR = new Thread(pr);
            hilos[i] = hiloR;   //Meto cada hilo al array
            hilos[i].start();
        }

        //Recorro el array para esperarlos:
        //Tengo que esperar a que los hilos terminene porque desde este hilo (el main)
        //después voy a acceder a esa variable, por tanto, han tenido que terminar antes
        // de acceder a ella.
        for (int i = 0; i < numHilos; i++) {
            hilos[i].join();
        }

        //Thread.sleep(1000); //MALA Solución de la condición de carrera (que el system.out imprima antes de que los hilos hayan terminado de ejecugtarse
        System.out.println("\n\nEl valor de la variable estática es " + variable);
        System.out.println("Termina el principal");
    }
}
