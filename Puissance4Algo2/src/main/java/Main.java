public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();

            Puissance4 puissance4 = new Puissance4();
            puissance4.init();
            puissance4.set(3, 0, 2);
            puissance4.set(3, 1, 2);
            puissance4.set(3, 2, 1);
            puissance4.set(2, 0, 1);
            puissance4.set(2, 1, 1);
            puissance4.set(1, 0, 1);
            puissance4.set(4, 1, 2);
            puissance4.set(3, 3, 1);
            puissance4.set(4, 2, 2);
            puissance4.set(4, 3, 1);


            puissance4.showBoard();
            System.out.println("Value board : "+puissance4.getValueBoard(2));
            System.out.println("MOVE : " + puissance4.bestCoup(1));


        long endTime = System.nanoTime();

        double executionTime = (endTime - startTime) / 1_000_000_000.0;
        System.out.printf("Temps d'exécution : %.4f secondes%n", executionTime);

    }
}
