package codigosClase.repasohilos;

public class MainHilosRepaso {

    public static final int NUM_HILOS = 10000;

    public static void main(String[] args) {
        HiloRepaso[] hilos = new HiloRepaso[NUM_HILOS];
        for (int i = 0; i < NUM_HILOS; i++) {
            HiloRepaso h;
            if (i < NUM_HILOS / 2) {
                //aumentar
                hilos[i] = new HiloRepaso(String.valueOf(i), true);
            } else {
                //disminuir
                hilos[i] = new HiloRepaso(String.valueOf(i), false);
            }
            hilos[i].start();
        }
        //Para acceder a la variable contador, todos los hilos han tenido que terminar
        for (int i = 0; i < NUM_HILOS; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Debería ser 0: " + HiloRepaso.contador);    //0

        HiloRepaso.contador = 0;
        for (int i = 0; i < NUM_HILOS; i++) {
            HiloRepaso h;
            if (i < 100) {
                //aumentar
                h = new HiloRepaso(String.valueOf(i), true);
            } else {
                //disminuir
                h = new HiloRepaso(String.valueOf(i), false);
            }
            h.start();
            //El código de arriba es esta línea:
            //new HiloRepaso(String.valueOf(i), i<100).start();
        }
        System.out.println("Debería ser -800: " + HiloRepaso.contador);    //-800

        HiloRepaso.contador = 0;
        for (int i = 0; i < NUM_HILOS; i++) {
            HiloRepaso h;
            if (i % 2 == 0) {
                //aumentar
                h = new HiloRepaso(String.valueOf(i), true);
            } else {
                //disminuir
                h = new HiloRepaso(String.valueOf(i), false);
            }
            h.start();
        }
        System.out.println("Debería ser 0: " + HiloRepaso.contador);    //0

    }
}
