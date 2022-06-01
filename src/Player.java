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

        for (int i = 0; i < array.length; i++) {   // ����� ������� ������ 10 � ����� ������
            String line = scanner.nextLine();
            stl.add(line);

        }
        System.out.println();
        System.out.println(stl.size());

        // scanner.close(); //������� ������� ������� ���, �� �� ��������.
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

            arrayLine = ss.split(";");  // ����� ������� �����, �������� ��� � ������ � ������

            for (String sss : arrayLine) {   // ����� ��������
                arrayLine2 = sss.split(",");

                // ������� ���� ���������� �������� ���� 10x10;
                for (int i = 0; i < array.length; i++) {
                    for (int j = 0; j < array[i].length; j++) {
                        // ������� �����


                        //  ��������� ������� ���������
                        if (i == Integer.parseInt(arrayLine2[0]) && j == Integer.parseInt(arrayLine2[1])
                                && Integer.parseInt(arrayLine2[0]) >= 0 && Integer.parseInt(arrayLine2[0]) < 10
                                && Integer.parseInt(arrayLine2[1]) >= 0 && Integer.parseInt(arrayLine2[1]) < 10) {
                            array[j][i] = 1;  // �������� ������


                            // �������� �� ArrayOutOfBound
                        }
                        if (Integer.parseInt(arrayLine2[0]) < 0 || Integer.parseInt(arrayLine2[0]) > 10
                                || Integer.parseInt(arrayLine2[1]) < 0 || Integer.parseInt(arrayLine2[1]) > 10) {

                            stl.clear();                    // ������� ����
                            for (int[] row : array)         // ������ ����� �������
                                Arrays.fill(row, 0);    // ���������� �������
                            System.out.println(stl);
                            System.out.println("������! ������������ ���������� �������, ���������� �����");
                            return false;

                        }
                        if (!checkShipLength()) {  // �������� �� ����� �������
                            stl.clear();                    // ������� ����
                            for (int[] row : array)         // ������ ����� �������
                                Arrays.fill(row, 0);    // ���������� �������
                            System.out.println(stl);
                            System.out.println("�������� ����� ������ �� ��������");
                            return false;
                        }
                    }
                }
                Cell cell = new Cell(Integer.parseInt(arrayLine2[0]), Integer.parseInt(arrayLine2[1]));
                cells.add(cell);   // � ����� ������ �� ���� �������
            }

            Ship ship = Ship.create(cells);
            cells.clear();    // ����� ������� ���� ������, ����� ���� �������� ���������� �� ����� ������ ��� ����� ������

            if (ship == null) {
                stl.clear();
                cells.clear();// ������� ����
                for (int[] row : array)         // ������ ����� �������
                    Arrays.fill(row, 0);
                System.out.println("������� ������ false (����� �� ������ Player)");
                return false;
            }
        }
        return true;
    }

    public boolean checkShipLength() { // ����� ��������� ����� ������ � ��������
        int[] sL = new int[array.length];   // ��������� ������, ������ ��� ����������� ����� ������.
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
        int[] controllArray = {15, 11, 11, 7, 7, 7, 3, 3, 3, 3};  // ����������� ������
        if (Arrays.equals(sL, controllArray)) {
            return true;
        }

        return false;
    }


//    boolean collides = false;
//                for (int xi = 0; xi < array.length; xi++) {
//        for (int yi = 0; yi < array[xi].length; yi++) {
//            if (1 == array[yi][xi]) {  // ���� ������� ����� ������ �������
//                if (ship.collide(new Cell(xi, yi))) {
//                    collides = true;
//                    stl.clear();                    // ������� ����
//                    for (int[] row : array)         // ������ ����� �������
//                        Arrays.fill(row, 0);    // ���������� �������
//
//                    // if collides, �� ����� ���������� false
//                    return false;
//                }
//            }
//        }
//    }



//    public boolean insetShip() {
//        // ��������������� �����. ��������� ���� �������� cell;
//
//        String[] arrayLine;
//        String[] arrayLine2;
//
//        for (String s : stl) {  // stl ��� ���� ��������
//            String ss = s;     //downCasting
//
//            arrayLine = ss.split(";");  // ����� ������� �����, �������� ��� � ������ � ������.
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
//                System.out.println(" ������� �� ��������");
//                stl.clear();                    // ������� ����
//                for (int[] row : array)         // ������ ����� �������
//                    Arrays.fill(row, 0);    // ���������� �������
//                return false;
//            } else {
//                ships.add(ship);
//                System.out.println(" ������� ���������");
//            }
//            cells.clear();
//        }
//        return true;
//    }

    public String getName() {
        return name;
    }


    //    public boolean checkShip() {
//        // �e��� ���������, ���������� �������.
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
//        // ������� �� ����� ������ ������� ������. ��� �� ��������, � ��������� ����� ���� ��� �������� ��� ������
//
//        return true;
//    }


    public void makeTurn(Player p) {
        while (true) {
            System.out.println(getName() + " ��������� �� �������� " + p.getName());
            p.drawField();
            Scanner scanner = new Scanner(System.in);
            System.out.println("������� ���������� X ��� �����: ");
            int x = scanner.nextInt();
            System.out.println("������� ���������� Y ��� �����: ");
            int y = scanner.nextInt();

            for (int i = 0; i < p.array.length; i++) {
                for (int j = 0; j < p.array[i].length; j++) {

                    // ������������ ������
                    if (i == x && j == y && p.array[y][x] == 1) {        //
                        p.array[y][x] = 2; // ����� (������ ���������� �����)
                        System.out.println("�����!");
                        p.drawField();
                        hit++;
                        System.out.println("� " + getName() + " ����� ��������� =  " + getHit());
                    }
                    if (p.array[y][x] == 0) {
                        p.array[y][x] = 3; // ����
                        System.out.println("����!");
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
//        // 4 ������
//        System.out.println("�������� ����������� ������� �� ����. ������ �����, �� ������!");
//        System.out.println("������ ���������� ���������������� ������� (������: " +
//                "x,y; x,y; x,y; x,y)");
//        // 3 ������
//        addShip();
//
//        System.out.println("������ ���������� ������� ������������� ������� (������: " +
//                "x,y; x,y; x,y;)");
//        addShip();
//
//        System.out.println("������ ���������� ������� ������������� ������� (������: " +
//                "x,y; x,y; x,y;)");
//
//        // 2 ������
//
//        addShip();
//
//        System.out.println("������ ���������� ������� ������������� ������� (������: " +
//                "x,y; x,�)");
//
//        addShip();
//
//        System.out.println("������ ���������� ������� ������������� ������� (������: " +
//                "x,y; x,y;)");
//
//        addShip();
//
//        System.out.println("������ ���������� �������� ������������� ������� (������: " +
//                "x,y; x,y;)");
//        addShip();
//
//        // 1 ������
//
//        System.out.println("������ ���������� ������� ������������� ������� (������: " +
//                "x,y;)");
//
//        addShip();
//
//        System.out.println("������ ���������� ������� ������������� ������� (������: " +
//                "x,y;)");
//
//        addShip();
//
//        System.out.println("������ ���������� �������� ������������� ������� (������: " +
//                "x,y;)");
//        addShip();
//
//        System.out.println("������ ���������� ���������� ������������� ������� (������: " +
//                "x,y; )");
//        addShip();
//
//        drawField();
//
//    }

// ���������� ������ ����� �������
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
//            // �������� ������ �������� � �������� ������.
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
//        System.out.println("������� ���������� ��������");
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
//                // �������� ������ �������� � �������� ������.
//
//                for (int i = 0; i < array.length; i++) {
//                    for (int j = 0; j < array[i].length; j++) {
//                        if (i == Integer.parseInt(arrayLine2[0]) && j == Integer.parseInt(arrayLine2[1]))
//                            array[i][j] = 1;
//                    }
//                }


// ����� �� SOF
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


//    ���� � ������� � ���������
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