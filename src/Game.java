import java.io.IOException;
import java.util.Random;

public class Game {
    public static void main(String[] args) throws IOException {

        Player player1 = new Player("Миша");
        Player player2 = new Player("Стив");

        do {
            System.out.println(player1.getName() + " расставляйте корабли");
          //  player1.writeToList();
           // player1.addShipToField();
        } while (player1.addShipToField() == false /* || player1.insetShip() == false || player1.writeToList() == false*/ );
        player1.drawField();
        player1.checkShipLength();

        do {
            System.out.println(player2.getName() + " расставляйте корабли");
            // player2.writeToList();
          //  player2.addShipToField();
        } while (player2.addShipToField() == false /* || player2.insetShip() == false || player2.writeToList() == false*/  );
        player2.drawField();
        player2.checkShipLength();


        System.out.println("Корабли: " + player1.getName());
        player1.drawField();

        System.out.println("Корабли: " + player2.getName());
        player2.drawField();


        Random random = new Random();
        int r = random.nextInt();
        if (r % 2 == 0) {
            while (true) {
                player1.makeTurn(player2);
                if (player1.getHit() == 4) {
                    System.out.println(player1.getName() + " is win!");
                    return;
                }
                player2.makeTurn(player1);
                if (player2.getHit() == 4) {
                    System.out.println(player2.getName() + " is win!");
                    return;
                }
            }
        } else {
            while (true) {
                player2.makeTurn(player1);
                if (player2.getHit() == 4) {
                    System.out.println(player2.getName() + " is win!");
                    return;
                }
                player1.makeTurn(player2);
                if (player1.getHit() == 4) {
                    System.out.println(player1.getName() + " is win!");
                    return;
                }
            }
        }
    }
}






