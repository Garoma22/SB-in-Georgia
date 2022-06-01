import java.util.ArrayList;
import java.util.List;


// Корабль это пустой лист
// проходимся по массиву кораблей и делаем из занятой клетки объект класса Cell;
// и добавляем эти объекты в лист, получается лист объектов.
// Ставим корабль. Первая проверка на валидность корабля.


class Ship {

    public final List<Cell> decks;    // создали список объектов типа Cell


//    создаем конструктор
    Ship(List<Cell> decks) {  // конструктор, нужен для определения,
        // что при создании корабля, используюем списко обхектов типа cell по имени decks
        this.decks = decks;
    }

    public static Ship create(List<Cell> decks) {

        int n = 0;

        int x = decks.get(0).getX();  // этим методом мы фиксим первый элемент списка, с которым и будем сравнивать вс остальные.
        int y = decks.get(0).getY();

        // метод проверяет, чтобы корабль стоял в линию по одной из осей
        for (Cell cell : decks) {
            if (cell.getX() == x && cell.getY() == y + n || cell.getX() == x + n && cell.getY() == y) {
                n++;


            } else {
                System.out.println("Появился невалидный корабль (вывод из метода класса Ship)");
                return null;
            }
        }
        return  new Ship(decks);
    }


    public boolean collide(Cell otherDeck) {  // какая-нибудь другая клатка вновь создаваемого корабля
        for (Cell deck : decks) { // идет по декам уже созданных кораблей
            if (deck.equals(otherDeck) || deck.nextTo(otherDeck) || deck.touchCorner(otherDeck))

                System.out.println("Ошибка! Корабли сталкиваются!");
            return true;
        }
        return false;
    }

}





















