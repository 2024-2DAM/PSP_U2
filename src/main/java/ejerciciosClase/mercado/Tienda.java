package ejerciciosClase.mercado;

public class Tienda {
    public static void main(String[] args) {
        Caja c1 = new Caja("c1", "juan", 3);
        Caja c2 = new Caja("c2", "luz", 2);
        Caja c3 = new Caja("c3", "alber", 5);
        Caja c4 = new Caja("c4", "ishak", 4);
        Caja c5 = new Caja("c5", "david", 1);

        /* Esto sería sin multihilo:
        c1.procesarCompra("Juan", 3);
        c2.procesarCompra("Luz", 2);
        c3.procesarCompra("Alber", 5);
        c3.procesarCompra("Ishak", 4);
        c3.procesarCompra("David", 1);
         */

        c1.start(); //Tengo acceso a .start porque heredo de Thread
        //Si implementara Runnable, lo tengo que envolver en un objeto Thread. Así:
        //Thread t1 = new Thread(c1);
        //t1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
    }
}
