package codigosClase.secretaria;

public class SecretariaMain {
    public static void main(String[] args) {
        Thread[] t = new Thread[7];
        for (int i = 0; i < 7; i++) {
            Secretaria s = new Secretaria("" + i);
            t[i] = new Thread(s);
            t[i].start();
        }
        for (int i = 0; i < 7; i++) {
            try {
                t[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        //TODO: esto es un problema de sincro
        System.out.println("TOTAL: " + Secretaria.contador);
    }
}
