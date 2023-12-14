package Lesson6;

/** 1. Создать и запустить программу для построения двоичного дерева.
 В цикле построить двести деревьев из 100 элементов.
 Данные, которыми необходимо заполнить узлы деревьев, представляются в виде чисел типа int.
Число, которое попадает в узел, должно генерироваться случайным образом в диапазоне от -100 до 100.
 2. Проанализировать, какой процент созданных деревьев являются несбалансированными.**/

import java.util.Stack;

public class main {
    public static void main(String[] args) {
        int balanceCounter = 0;
        // количество создаваемых деревьев
        int capacity = 200;
        Tree tree = new Tree();

        for (int trees = 0; trees < capacity; trees++) {

            // создаем дерево из случайных чисел
            for (int i = 0; i < 100; i++) {
                int data = Generate.generate(-100, 100);
                tree.insert(data);
            }

             // анализируем дерево на сбалансированность
            /**Сбалансированное дерево – это дерево, на котором  максимальное расстояние от корня до любого листа
             * различается не более чем на один , чем минимальное расстояние от корня до любого листа**/

            int min = tree.isMin();
            int max = tree.isMax();

            // вносим результат в  кэш
            if (Math.abs(max - min) > 1) balanceCounter++;
        }

        // Анализируем кэш
        int balanceTree = balanceCounter / capacity + 100;
        System.out.println("Процент сбалансированного дерева =  " + balanceTree);

    }
}

class Tree {
    public Node root;

    public int isMin() {
        Node current;
        int min = 0;
        current = root;
        while (current != null) {
            current = current.leftChild;
            min++;
        }
        return min;
    }

    public int isMax() {
        Node current;
        int max = 0;
        current = root;
        while (current != null) {
            current = current.rightChild;
            max++;
        }
        return max;
    }

    public void insert(int value) {
        Node newNode = new Node();
        newNode.data = value;
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (value < current.data) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public void printTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int gaps = 32;
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.data);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);
                    if (temp.leftChild != null ||
                            temp.rightChild != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("__");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');

            }
            System.out.println();
            gaps /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        }
        System.out.println(separator);
    }
}

class Node {
    public int data;
    public Node leftChild;
    public Node rightChild;
}

class Generate {
    public static int generate(int min, int max) {
        max -= min;
        return (int) ((Math.random() * ++max) + min);
    }
}
