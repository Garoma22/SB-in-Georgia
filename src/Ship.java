import java.util.ArrayList;
import java.util.List;


// ������� ��� ������ ����
// ���������� �� ������� �������� � ������ �� ������� ������ ������ ������ Cell;
// � ��������� ��� ������� � ����, ���������� ���� ��������.
// ������ �������. ������ �������� �� ���������� �������.


class Ship {

    public final List<Cell> decks;    // ������� ������ �������� ���� Cell


//    ������� �����������
    Ship(List<Cell> decks) {  // �����������, ����� ��� �����������,
        // ��� ��� �������� �������, ����������� ������ �������� ���� cell �� ����� decks
        this.decks = decks;
    }

    public static Ship create(List<Cell> decks) {

        int n = 0;

        int x = decks.get(0).getX();  // ���� ������� �� ������ ������ ������� ������, � ������� � ����� ���������� �� ���������.
        int y = decks.get(0).getY();

        // ����� ���������, ����� ������� ����� � ����� �� ����� �� ����
        for (Cell cell : decks) {
            if (cell.getX() == x && cell.getY() == y + n || cell.getX() == x + n && cell.getY() == y) {
                n++;


            } else {
                System.out.println("�������� ���������� ������� (����� �� ������ ������ Ship)");
                return null;
            }
        }
        return  new Ship(decks);
    }


    public boolean collide(Cell otherDeck) {  // �����-������ ������ ������ ����� ������������ �������
        for (Cell deck : decks) { // ���� �� ����� ��� ��������� ��������
            if (deck.equals(otherDeck) || deck.nextTo(otherDeck) || deck.touchCorner(otherDeck))

                System.out.println("������! ������� ������������!");
            return true;
        }
        return false;
    }

}





















