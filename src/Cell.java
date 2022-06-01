import java.util.Objects;

class Cell {
    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public boolean nextTo(Cell other) {
        return (this.x == other.x && Math.abs(this.y - other.y) == 1 ||
                Math.abs(this.x - other.x) == 1 && this.y == other.getY());
    }

    public boolean touchCorner(Cell other) {
        return (Math.abs(this.x - other.x) == 1 && Math.abs(this.y - other.y) == 1);
    }
    @Override
    // ЭТОТ МЕТОД НЕОБХОДИМ, если мы хотим сравнивать обекты между собой не по указателям(именам?), а по полям.
    // такое переопределение позволяет сравнивать обхекты структурно. То есть, через equals можно сравнивать объекты как сущности.
    // метод позволяет не допускать сравниваняи обхектов разных типов.

    public boolean equals(Object o) {
        if (!(o instanceof Cell)) {  // проверка соответствия типов, является ли "о" экземпляром указанного типа
            return false;
        }
        Cell cell = (Cell) o;  // если да, то ретюрним поля.
        return getX() == cell.getX() && getY() == cell.getY();
    }
    // если все ок, все проверки прошли, то говорим что иксы и игреки равны
    // этим же параметрам другого cell, то есть это два обхекта, ссылающиеся на один участок памаяти.
    // (что в методе collide класса Ship станет условием столкновения).

    @Override
    public int hashCode() { // метод для генерации целочисленного кода обхекта
        int hash = 7;
        hash = 31 * hash + this.x;
        hash = 31 * hash + this.y;
        return hash;
    }

    @Override
    public String toString() {
        return x + " : " + y;
    }
}





