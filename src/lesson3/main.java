package lesson3;

/**     1. Создать класс для реализации дека (двухсторонняя очередь).
       2. Создать класс для реализации приоритетной очереди (выбрать только один из вариантов)
       3. описать метод проверки скобочной последовательности (см. код урока, комментарий под главным классом)**/

import java.lang.reflect.Array;
import java.util.Stack;

public class main {
    public static void main(String[] args) {
        System.out.println("Hello in Lesson 3");

    }
}

// * Двусторонняя очередь  – очередь, у которой нет явно выраженного конца и начала.

class Deque {
    int size;
    int head = -1;
    int tail = 0;
    int[] data;

    /** проверка что очередь  пустая , если head == -1, то она пуста */
    boolean isEmpty() {
        return head == tail;
    }

    /** проверка что очередь заполнена**/
    boolean isFull() {
        return ((head == 0 && tail == size - 1) || head == tail + 1);
    }

    /** добавить элемент в начало очереди **/
    void pushFront(int value) {
        if (isFull()) {
            System.out.println("Переполнение");
            return;
        }

        if (head < 1) {
            head = size - 1;
        } else {
            head++;
            data[head] = value;
        }
    }

    /** вставка в конец очереди */
    void pushBack(int value) {

        if (isFull()) {
            System.out.println("Переполнение");
            return;
        }

        if (head == -1) {
            head = 0;
            tail = 0;
        } else if (tail == size - 1) {
            tail = 0;
        } else {
            tail = tail + 1;
        }

        data[tail] = value;
    }

    /** получить элемент из начала очереди **/
    int popFront() {
        if (++head == size)
            head = 0;
        return data[head];
    }

    /** получить элемент из хвоста очереди **/
    int popBack() {
        int res = data[tail];
        if (--tail < 0)
            tail = size - 1;
        return res;
    }
}

/** задача 2 Реализация приоритетной очереди -**/

class MyPriorityQueue{
    int[] queue;
    int size = queue.length;
    int head = 0;
    int tail = 0;

    boolean isEmpty(){                  // проверка массива на пустоту
        return (this.head == this.tail);
    }

    boolean isFull(){                   // проверка массива на переполненость
        return tail-1 == size;
    }

    void pushElement(int element){

        if(isFull()){
            int array[]= new int [size*2];
            System.arraycopy(queue,0,array,0,size);
            queue = array;
        }

        if (isEmpty()){
            queue[0] = element;
        }else {
            for (int i = 0; i < this.size; i++) {
                if (queue[i]>element){
                    int temp = queue[i];
                    queue[i] = element;
                    System.arraycopy(queue,i+1,queue,i,size - i - 1);
                    queue[i] = temp;
                    tail++;
                }
            }
        }
    }

    int popElement() {
        int element = 0;
        if (isEmpty()) {
            System.out.println("Массив пуст");
        } else {
            element = queue[0];
            System.arraycopy(queue, 1, queue, 0, size - 1);
            tail--;
        }
        return element;
    }
}

/**задача 3 Проверка скобочной последовательности**/

class Check{
    Stack stack = new Stack();

    boolean balance = true;
    public void checker(String str){
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(str.charAt(i));
            } else if (str.charAt(i) == ')') {

                if (!stack.isEmpty()) {

                    Character resultStackPop = (Character) stack.pop();
                    if ((resultStackPop.equals('(') && str.charAt(i) != ')')) {
                        stack.pop();
                        balance = false;
                        break;
                    }

                } else if (stack.isEmpty()) {
                    System.out.println(" такая скобка ) не может быть начальной");
                    balance = false;
                    break;
                }
            }
        }

        if(balance == true){
            System.out.println("Скобки сбалансированы");
        }else {
            System.out.println("Скобки не сбалансированы");
        }
    }
}
