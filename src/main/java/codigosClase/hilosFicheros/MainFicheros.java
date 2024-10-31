package codigosClase.hilosFicheros;

public class MainFicheros {
    public static final int NUM_HILOS = 50000;

    public static void main(String[] args) {
        //Crear 100 hilos que sumen y 100 hilos que resten
        int i = 0;
        EscribeFichero[] hilos = new EscribeFichero[NUM_HILOS];
        do {
            hilos[i] = new EscribeFichero(i % 2 == 0);
            hilos[i].start();
            i++;
        } while (i < NUM_HILOS);

        //Necesito esperar a que terminen todos los hilos para poder acceder a EscribeFichero.valor
        for (EscribeFichero hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Ver qué hay en la variable EscribeFichero.valor
        System.out.println("--->" + EscribeFichero.getValor());
    }
}
