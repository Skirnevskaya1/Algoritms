package lesson5;

//        1. Написать программу по возведению числа в степень с помощью рекурсии.
//        2. *** Написать программу обхода шахматным конём доски (рекурсивно)

public class main {
    public static void main(String[] args) {
        System.out.println("Hello Recursion");
        int result = Recursion.arifmetic(2, 3);
        System.out.println("результат  = " + result);
        System.out.println("Tower");

        Recursion.hanoy(4, 1, 3, 2); // ханойская башня
        System.out.println("Horse");

        HorseGo horseGo = new HorseGo(6);// задаем размер доски
        horseGo.start();
    }
}

class HorseGo {

    int x ;
    int y ;
    int counter = 0;
    int[][] desk;
    int[][] variants = {{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1},{-2,1},{-1,2}};

    public HorseGo(int size){
        this.x = size;
        this.y = size;
    }

    public void start(){
        createDesk();
        setHorse(1,1);
        print_desk();

    }

    private boolean setHorse(int x, int y){

        if(x < 0||x >= this.x||y < 0 || y >= this.y)
            return false;

        if(desk[x][y] != 0)
            return false;

        if(counter == this.x * this.y)
            return true;

        // рукурсия
        counter ++;
        desk[x][y] = counter;
        for (int i = 0; i < 8; i++){
            int x1 = x + variants[i][0];
            int y1 = y + variants[i][1];
          if(  setHorse(x1,y1))
            return true;
        }
        return false;

    }

     private void createDesk(){
         desk = new int[x][y];
        for (int i = 0; i < desk.length; i++) {
            for (int j = 0; j < desk[i].length; j++) {
                desk[i][j] = 0;
            }
        }
    }


       private void print_desk(){
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(" "+ desk[i][j]);
            }
            System.out.println("");
        }
    }
}

class Recursion {

    /** Задача 1 степень числа  */

    public static int arifmetic(int number, int stepen) {

        int result = 1;
        if (stepen > 0) {
            result = number * arifmetic(number, --stepen);
            System.out.println(result);
        }
        return result;
    }

    /**ханойская башня*/

    public static void hanoy(int size, int from, int to, int temp) {

        if (size > 0) {
            hanoy(size - 1, from, temp, to);
            System.out.println(from + "->" + to);
            hanoy(size - 1, temp, to, from);
        }
    }
}
