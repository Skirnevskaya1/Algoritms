package lesson1;

//    Описать простейшие алгоритмы (код + блок-схема):
//        1. возведение в степень
//        2. возведение в степень (с использованием свойства чётности степени)
//        3. получить сумму всех чисел в ряду от 0 до 100.

public class main {
    public static void main(String[] args) {
        System.out.println("Hello");
        int foot = 2;
        int degree = 11;
        Calculation numberInDegree = new Calculation();
        numberInDegree.numberInDegree(foot, degree);
        numberInDegree.numberInDegreeParity(foot, degree);
        numberInDegree.summa();
    }
}

class Calculation {

    public void numberInDegree(int foot, int degree) {
        long current = System.nanoTime() / 1000000000;
        int result = 1;
        for (int i = 1; i <= degree; i++) {
            result = result * foot;
        }
        System.out.println("Результат. Число " + foot + " в степени " + degree + " = " + result);
        System.out.println("Время  " + current);
    }

    // Возведение в степень с учетом четности
    public void numberInDegreeParity(int num, int degree) {
        long current = System.nanoTime() / 1000000000;
        // проверим на четность
        int result = 1;
        if (degree % 2 == 0) {

            while (degree > 0) {
                if (degree % 2 == 0) {
                    degree /= 2;
                    num *= num;
                } else {
                    degree--;
                    result *= num;
                }
            }
            System.out.println("Результат. Число " + num + " в степени " + degree + " = " + result);

        } else {
            System.out.println("степень, в которую возводим число - нечетная");
        }
        System.out.println("Время  " + current);
    }

    public void summa() {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum = sum + i;
        }
        System.out.println("Сумма элементов =   " + sum);
    }
}