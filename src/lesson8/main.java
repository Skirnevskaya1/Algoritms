package lesson8;

public class main {
    public static void main(String[] args) {

        HashMap myHashMap = new HashMap();

        myHashMap.insert(20, "Барсик");
        myHashMap.insert(558, "Кот");
        myHashMap.insert(12, "Мишка");
        myHashMap.insert(8, "Вера");
        myHashMap.insert(57, "Котик");
        myHashMap.insert(80, "Бегемот");
        myHashMap.insert(227, "Хвостик");
        myHashMap.insert(115, "Алиса");
        myHashMap.insert(205, "Тишка");
        myHashMap.insert(77, "Вася");

        myHashMap.showHashMap();

        System.out.println(" ");

        Node searchValue = myHashMap.find(80);
        if (searchValue != null) {
            System.out.println("для Key = " + searchValue.key + " value = " + searchValue.value);
        } else {
            System.out.println("Значение для ключа не найдено ");
        }

        System.out.println(" ");
        myHashMap.remove(227);

        myHashMap.showHashMap();

    }
}

class HashMap {
    private static final int SIZETABLE = 25;
    private static final int HASHPARAMETER = 10;
    private Node hashTableNode[];
    private Node top[];

    public HashMap() {
        hashTableNode = new Node[SIZETABLE];
        top = new Node[SIZETABLE];
        for (int i = 0; i < SIZETABLE; i++) {
            hashTableNode[i] = null;
            top[i] = null;
        }

    }

    public void insert(int key, String value) {
        int index = hashFunction(key);
        System.out.println("key = " + key + " hash = " + index);
        Node newNode = new Node();

        if (hashTableNode[index] == null) {
            hashTableNode[index] = newNode;
            newNode.next = null;
            newNode.key = key;
            newNode.value = value;

        } else {
            Node currentNode = hashTableNode[index];
            hashTableNode[index] = newNode;
            newNode.key = key;
            newNode.next = currentNode;
            newNode.value = value;
        }
    }

    public Node find(int key) {
        int index = hashFunction(key);
        Node current = hashTableNode[index];

        while (current != null) {
            if (current.key == key) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void remove(int key) {
        int index = hashFunction(key);
        Node previous = null;
        Node current = hashTableNode[index];

        System.out.println("Удаляем элемент с ключом = " + key);

        while (current != null) {

            if (current.key == key) {
                if (previous != null) {
                    previous.next = current.next;
                } else {
                    current = current.next;
                }
            }

            previous = current;
            current = current.next;
        }
    }

    public void showHashMap() {
        System.out.println("  ");
        System.out.println(" Хэш таблица ");

        for (int i = 0; i < SIZETABLE; i++) {
            if (hashTableNode[i] != null) {

                Node current = hashTableNode[i];

                while (current != null) {
                    System.out.print(" key = " + current.key + " value = " + current.value + "; ");
                    current = current.next;
                }
                System.out.println("  ");
            }
        }
    }

    private int hashFunction(int key) {
        return key % HASHPARAMETER;
    }

}

class Node {
    Node next;
    int key;
    String value;
}
