public class Main {

    public static void main(String[] args) {
        Puissance4 puissance4 = new Puissance4();
        puissance4.init();
        puissance4.set(3, 0, 0);
        puissance4.set(3, 0, 1);
        puissance4.set(3, 1, 2);
        puissance4.set(3, 2, 1);
        puissance4.set(2, 0, 1);
        puissance4.set(2, 1, 1);
        puissance4.set(2, 2, 1);
        puissance4.set(1, 0, 2);
        puissance4.set(3, 3, 2);
        puissance4.set(4, 0, 2);
        puissance4.set(1, 1, 0);
        puissance4.set(0, 0, 2);
        puissance4.set(2, 3, 2);
        puissance4.set(4, 2, 2);
        puissance4.set(5, 0, 2);
        puissance4.set(4, 1, 1);
        puissance4.set(3, 4, 1);
        puissance4.set(4, 2, 2);
        puissance4.set(2, 4, 0);
        puissance4.set(4, 3, 1);
        puissance4.set(4, 4, 0);
        puissance4.set(5, 1, 1);
        puissance4.set(6, 0, 2);
        puissance4.set(6, 1, 0);
        puissance4.set(0, 2, 0);
        puissance4.set(5, 1, 1);
        puissance4.set(5, 2, 2);
        puissance4.set(2, 4, 1);
        puissance4.set(4, 4, 2);
        puissance4.set(3, 5, 1);
        puissance4.set(4, 4, 2);
        puissance4.set(2, 5, 2);
        puissance4.set(4, 5, 1);
        puissance4.set(6, 1, 2);
        puissance4.set(6, 2, 1);
        puissance4.set(6, 3, 2);
        puissance4.set(5, 3, 1);
        puissance4.set(5, 4, 2);
        puissance4.set(5, 5, 1);
        puissance4.set(0, 1, 2);
        puissance4.set(0, 2, 1);
        puissance4.set(0, 3, 2);
        puissance4.set(0, 4, 1);
        puissance4.set(0, 5, 2);
        puissance4.set(1, 1, 1);
        puissance4.set(1, 2, 2);
        puissance4.set(1, 4, 1);





















        puissance4.set(0, 3, 0);
        puissance4.showBoard();
        System.out.println("Value board : "+puissance4.getValueBoard(2));
        System.out.println("MOVE : " + puissance4.bestCoup(1));
    }
}
