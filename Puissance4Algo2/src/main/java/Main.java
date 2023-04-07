public class Main {

    public static void main(String[] args) {
        Puissance4 puissance4 = new Puissance4();
        puissance4.init();
        puissance4.set(0, 0, 2);
        puissance4.set(0, 1, 2);
        puissance4.set(1, 0, 1);
        //puissance4.set(2, 0, 1);
        puissance4.set(3, 0, 1);
        puissance4.set(3, 1, 1);
        puissance4.set(3, 2, 2);
        puissance4.set(3, 3, 1);
        puissance4.set(3, 4, 2);
        puissance4.set(5, 0, 2);
        puissance4.set(6, 0, 2);
        puissance4.set(5, 1, 1);
        puissance4.set(6, 1, 1);
        puissance4.showBoard();
        System.out.println("Value board : "+puissance4.getValueBoard(2));
        System.out.println("MOVE : " + puissance4.bestCoup(1));
    }
}
