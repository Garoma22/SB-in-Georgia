import java.util.*;

class Player {

    private String name;
    private int[][] array;
    private List<String> stl;
    private int hit;
    private List<Cell> cells;
    private List<Ship> ships;


    public Player(String name) {
        this.name = name;
        this.array = new int[10][10];
        this.stl = new ArrayList<>();
        this.cells = new ArrayList<>();
        this.ships = new ArrayList<>();

    }

    public void drawField() {
        for (int i = 0; i < array.length; i++) {
            System.out.println();
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0) {
                    System.out.print(" _ ");
                } else if (array[i][j] == 1) {
                    System.out.print(" 0 ");
                } else if (array[i][j] == 2) {
                    System.out.print(" X ");
                } else if (array[i][j] == 3) {
                    System.out.print(" * ");
                }
            }
        }
        System.out.println();
        System.out.println();
    }

    public boolean writeToList() {

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < array.length; i++) {   // длина массива задана 10 в полях класса
            String line = scanner.nextLine();
            stl.add(line);

        }
        System.out.println();
        System.out.println(stl.size());

        // scanner.close(); //СКАННЕР ЛОГИЧНО ЗАКРЫТЬ ТУТ, НО ОН РУГАЕТСЯ.
        return true;
    }

    public boolean addShipToField() {
        if (!writeToList()) {
            return false;
        }

        String[] arrayLine;
        String[] arrayLine2;

        for (String s : stl) {


            String ss = s;     //downCasting

            arrayLine = ss.split(";");  // берем элемент листа, сплитуем его и кладем в массив

            for (String sss : arrayLine) {   // линия кораблей
                arrayLine2 = sss.split(",");

                // ГЛАВНЫЙ ЦИКЛ перебирает основное поле 10x10;
                for (int i = 0; i < array.length; i++) {
                    for (int j = 0; j < array[i].length; j++) {
                        // перебор палуб


                        //  ЗАПОЛНЯЕМ МАССИВА ЕДИНИЦАМИ
                        if (i == Integer.parseInt(arrayLine2[0]) && j == Integer.parseInt(arrayLine2[1])
                                && Integer.parseInt(arrayLine2[0]) >= 0 && Integer.parseInt(arrayLine2[0]) < 10
                                && Integer.parseInt(arrayLine2[1]) >= 0 && Integer.parseInt(arrayLine2[1]) < 10) {
                            array[j][i] = 1;  // инверсия матриц


                            // проверка на ArrayOutOfBound
                        }
                        if (Integer.parseInt(arrayLine2[0]) < 0 || Integer.parseInt(arrayLine2[0]) > 10
                                || Integer.parseInt(arrayLine2[1]) < 0 || Integer.parseInt(arrayLine2[1]) > 10) {

                            stl.clear();                    // очищаем лист
                            for (int[] row : array)         // хитрый метод очистки
                                Arrays.fill(row, 0);    // двумерного массива
                            System.out.println(stl);
                            System.out.println("ОШИБКА! Некорректная координата корабля, попробуйте снова");
                            return false;

                        }
                        if (!checkShipLength()) {  // проверка на длину корабля
                            stl.clear();                    // очищаем лист
                            for (int[] row : array)         // хитрый метод очистки
                                Arrays.fill(row, 0);    // двумерного массива
                            System.out.println(stl);
                            System.out.println("Неверная длина одного из кораблей");
                            return false;
                        }
                    }
                }
                Cell cell = new Cell(Integer.parseInt(arrayLine2[0]), Integer.parseInt(arrayLine2[1]));
                cells.add(cell);   // в листе клеток на один корабль
            }

            Ship ship = Ship.create(cells);
            cells.clear();    // ВАЖНО ЧИСТИТЬ ЛИСТ КЛЕТОК, ЧТОБЫ НОВЫ ЙКОРАБЛЬ СОЗДАВАЛСЯ ИЗ НОВЫХ КЛЕТОК БЕЗ УЧЕТА СТАРЫХ

            if (ship == null) {
                stl.clear();
                cells.clear();// очищаем лист
                for (int[] row : array)         // хитрый метод очистки
                    Arrays.fill(row, 0);
                System.out.println("Корабль вернул false (вывод из класса Player)");
                return false;
            }
        }
        return true;
    }

    public boolean checkShipLength() { // метод проверяет длину строки в символах
        int[] sL = new int[array.length];   // служебный массив, служит для определения длины строки.
        int counter = 0;
        int n = 0;

        for (String x : stl) {
            for (int i = 0; i < x.length(); i++) {
                counter++;
            }
            sL[n] = counter;
            counter = 0;
            n++;
        }
        int[] controllArray = {15, 11, 11, 7, 7, 7, 3, 3, 3, 3};  // контрольный массив
        if (Arrays.equals(sL, controllArray)) {
            return true;
        }

        return false;
    }


//    boolean collides = false;
//                for (int xi = 0; xi < array.length; xi++) {
//        for (int yi = 0; yi < array[xi].length; yi++) {
//            if (1 == array[yi][xi]) {  // если единица равна ячейке массива
//                if (ship.collide(new Cell(xi, yi))) {
//                    collides = true;
//                    stl.clear();                    // очищаем лист
//                    for (int[] row : array)         // хитрый метод очистки
//                        Arrays.fill(row, 0);    // двумерного массива
//
//                    // if collides, то метод возвращает false
//                    return false;
//                }
//            }
//        }
//    }



//    public boolean insetShip() {
//        // вспомогательный метод. заполняет лист объектов cell;
//
//        String[] arrayLine;
//        String[] arrayLine2;
//
//        for (String s : stl) {  // stl это лист стрингов
//            String ss = s;     //downCasting
//
//            arrayLine = ss.split(";");  // берем элемент листа, сплитуем его и кладем в массив.
//            for (String sss : arrayLine) {
//                arrayLine2 = sss.split(",");
//
//                Cell cell = new Cell(Integer.parseInt(arrayLine2[0]), Integer.parseInt(arrayLine2[1]));
//                cells.add(cell);
//            }
//
//            Ship ship = Ship.create(cells);
//
//            if (ship == null) {
//                System.out.println(" Корабль НЕ создался");
//                stl.clear();                    // очищаем лист
//                for (int[] row : array)         // хитрый метод очистки
//                    Arrays.fill(row, 0);    // двумерного массива
//                return false;
//            } else {
//                ships.add(ship);
//                System.out.println(" Корабль СОЗДАЕТСЯ");
//            }
//            cells.clear();
//        }
//        return true;
//    }

    public String getName() {
        return name;
    }


    //    public boolean checkShip() {
//        // мeтод проверяет, валидность корабля.
//
//        int n = 0;
//        for (Cell cell : cells) {
//            int x = cell.getX();
//            int y = cell.getY();
//
//            if (cell.getX() == x && cell.getY() == y + n || cell.getX() == x + n && cell.getY() == y) {
//              n++;
//            } else {
//               return false;
//            }
//        }
//        // удалить всё чтобы другой корабль писать. Или не удалаять, а сохранить такой лист для проверки тач корнер
//
//        return true;
//    }


    public void makeTurn(Player p) {
        while (true) {
            System.out.println(getName() + " Стреляйте по кораблям " + p.getName());
            p.drawField();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите координаты X для атаки: ");
            int x = scanner.nextInt();
            System.out.println("Введите координаты Y для атаки: ");
            int y = scanner.nextInt();

            for (int i = 0; i < p.array.length; i++) {
                for (int j = 0; j < p.array[i].length; j++) {

                    // прокручиваем массив
                    if (i == x && j == y && p.array[y][x] == 1) {        //
                        p.array[y][x] = 2; // попал (убрать магическое число)
                        System.out.println("Попал!");
                        p.drawField();
                        hit++;
                        System.out.println("У " + getName() + " число попаданий =  " + getHit());
                    }
                    if (p.array[y][x] == 0) {
                        p.array[y][x] = 3; // мимо
                        System.out.println("Мимо!");
                        p.drawField();
                        return;
                    }
                }
            }
        }
    }

    public int getHit() {
        return hit;
    }

    public int[][] getArray() {
        return array;
    }

    @Override
    public String toString() {
        return "Player{" +
                "cells=" + cells +
                '}';
    }
}






















//    public void writeCoordinates() throws FileNotFoundException {
//        // 4 палубы
//        System.out.println("Начинаем расставлять корабли на поле. Другой игрок, не смотри!");
//        System.out.println("Ведите координаты четырехпалубного корабля (формат: " +
//                "x,y; x,y; x,y; x,y)");
//        // 3 палубы
//        addShip();
//
//        System.out.println("Ведите координаты первого трехпалубного корабля (формат: " +
//                "x,y; x,y; x,y;)");
//        addShip();
//
//        System.out.println("Ведите координаты второго трехпалубного корабля (формат: " +
//                "x,y; x,y; x,y;)");
//
//        // 2 палубы
//
//        addShip();
//
//        System.out.println("Ведите координаты первого двухпалубного корабля (формат: " +
//                "x,y; x,у)");
//
//        addShip();
//
//        System.out.println("Ведите координаты второго двухпалубного корабля (формат: " +
//                "x,y; x,y;)");
//
//        addShip();
//
//        System.out.println("Ведите координаты третьего двухпалубного корабля (формат: " +
//                "x,y; x,y;)");
//        addShip();
//
//        // 1 палуба
//
//        System.out.println("Ведите координаты первого однопалубного корабля (формат: " +
//                "x,y;)");
//
//        addShip();
//
//        System.out.println("Ведите координаты второго однопалубного корабля (формат: " +
//                "x,y;)");
//
//        addShip();
//
//        System.out.println("Ведите координаты третьего однопалубного корабля (формат: " +
//                "x,y;)");
//        addShip();
//
//        System.out.println("Ведите координаты четвертого однопалубного корабля (формат: " +
//                "x,y; )");
//        addShip();
//
//        drawField();
//
//    }

// реализайия метода через консоль
//    public void addShip() {
//
//        Scanner scanner = new Scanner(System.in);
//        String[] arrayLine;
//        String[] arrayLine2;
//        String line = scanner.nextLine();
//
//
//        arrayLine = line.split(";");
//
//        for (String s : arrayLine) {
//            arrayLine2 = s.split(",");
//
//            // проводим запись значений в основной массив.
//
//            for (int i = 0; i < array.length; i++) {
//                for (int j = 0; j < array[i].length; j++) {
//                    if (i == Integer.parseInt(arrayLine2[0]) && j == Integer.parseInt(arrayLine2[1])) {
//                        array[i][j] = 1;
//
//                    }
//                }
//            }
//        }
//    }


//    public void writeToFile(File file) {
//
//        System.out.println("Введите координаты кораблей");
//        String outputFileName = file.getName();
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
//                String line;
//
//                while (!(line = reader.readLine()).equals("stop")) {
//                    writer.write(line + '\n');
//                    writer.flush();
//
//                }
//                reader.reset();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

//    public void readShip(File file) throws IOException {
//        Scanner scanner = new Scanner(file);
//        String[] arrayLine;
//        String[] arrayLine2;
//
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//
//            if (line.equals("stop")) {
//                return;
//            }
//
//            arrayLine = line.split(";");
//            for (String s : arrayLine) {
//                arrayLine2 = s.split(",");
//
//                // проводим запись значений в основной массив.
//
//                for (int i = 0; i < array.length; i++) {
//                    for (int j = 0; j < array[i].length; j++) {
//                        if (i == Integer.parseInt(arrayLine2[0]) && j == Integer.parseInt(arrayLine2[1]))
//                            array[i][j] = 1;
//                    }
//                }


// метод из SOF
//    public void writeToFile3() throws IOException {
//        File file = new File("Shipsfff " + getName()) ;
//        file.createNewFile();
//        Scanner scanner = new Scanner(System.in);
//        PrintWriter pw = new PrintWriter(file);
//
//        String line = scanner.nextLine();
//        List<String> ans = new ArrayList<>();
//
//        while (scanner.hasNextLine()) {
//
//            ans.add(line);
//            pw.println(line);
//        }
//            pw.close();
//    }


//        Scanner scannerWTF = new Scanner(System.in);
//        File file1 = new File("Battlefield_2");
//        file1.createNewFile();
//        PrintWriter pw1 = new PrintWriter(file1);
//
//        while (scannerWTF.hasNextLine()){
//            pw1.println();
//        }
//        scannerWTF.close();
//  }


//    Поле с челками и отступами
//   System.out.print("   1  2  3  4  5  6  7  8  9  10");
//    int n = 1;
//        for (int i = 0; i < array.length; i++) {
//        System.out.println();
//        System.out.print(n + " ");
//        n++;
//        for (int j = 0; j < array[i].length; j++) {
//            if (n < 11 ) {
//                System.out.print(" _ ");
//
//            }
//            else{
//                System.out.print("_  ");
//            }
//        }
//    }
//}