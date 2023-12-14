package lesson7;

//Реализовать программу, в которой задается граф из 10 вершин.
// Задать ребра и найти кратчайший путь с помощью поиска в ширину.

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class main {
    public static void main(String[] args) {

        Graf graf = new Graf();
        initGraf(graf);

        System.out.println("Начальный граф");

        graf.bfc();

        System.out.println("Расчет минимального пути");
        graf.showGraf();

        System.out.println("Расчет минимального маршрута");
        graf.showWays();
    }

    public static void initGraf(Graf graf) {
        graf.addVertex('A');
        graf.addVertex('B');
        graf.addVertex('C');
        graf.addVertex('F');
        graf.addVertex('G');
        graf.addVertex('E');
        graf.addVertex('F');
        graf.addVertex('J');
        graf.addVertex('P');
        graf.addVertex('Q');


        graf.addEdge(0, 1, 1);
        graf.addEdge(0, 2, 2);
        graf.addEdge(0, 3, 3);
        graf.addEdge(1, 3, 2);
        graf.addEdge(1, 4, 6);
        graf.addEdge(2, 5, 7);
        graf.addEdge(3, 5, 3);
        graf.addEdge(3, 6, 2);
        graf.addEdge(4, 7, 7);
        graf.addEdge(5, 9, 8);
        graf.addEdge(5, 6, 5);
        graf.addEdge(6, 7, 4);
        graf.addEdge(6, 9, 10);
        graf.addEdge(7, 8, 11);
        graf.addEdge(7, 9, 15);
        graf.addEdge(8, 9, 6);
    }
}

class Graf {
    private final int MAX_VERTEX_SIZE = 10;
    private final int INFINITY = 1_000_000_000;
    private Vertex vertexList[];
    private int matrix[][];
    private int counterVertex;
    private Queue<Integer> queue;

    Stack<Integer> stackWay = new Stack();

    // конструктор
    public Graf() {
        counterVertex = 0;
        queue = new PriorityQueue<>();
        vertexList = new Vertex[MAX_VERTEX_SIZE];
        matrix = new int[MAX_VERTEX_SIZE][MAX_VERTEX_SIZE];
        for (int i = 0; i < MAX_VERTEX_SIZE; i++) {
            for (int j = 0; j < MAX_VERTEX_SIZE; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public void showWays() {
        for (int vetrex = 1; vetrex < vertexList.length; vetrex++) {
            way(vetrex);
        }
    }

    // возвращаемся в вершину А через другие вершины по минимальному пути
    public void way(int vertexStart) {
        int initVertexIndex = vertexStart;
        stackWay.push(initVertexIndex);
        char initVertex = vertexList[initVertexIndex].getLabel();

        while (initVertexIndex != 0) {
            for (int i = 0; i < MAX_VERTEX_SIZE; i++) {
                int initVertexMarker = vertexList[initVertexIndex].getMarkerVertex();
                int weightEdge = matrix[i][initVertexIndex];
                int vertexMarker = vertexList[i].getMarkerVertex();

                if (weightEdge != 0 && (initVertexMarker - weightEdge) == vertexMarker) {
                    stackWay.push(i);
                    initVertexIndex = i;
                }
            }
        }

        System.out.println("Минимальный путь от вершины А  до " + initVertex);
        while (!stackWay.isEmpty()) {
            System.out.print(" " + vertexList[stackWay.pop()].getLabel());
        }
        System.out.println("");
    }

    //Поиск минимального значения пути от вершины А
    public void bfc() {
        vertexList[0].setVisited(true);
        vertexList[0].setMarkerVertex(0);
        queue.add(0);
        int nextVertex;

        while (!queue.isEmpty()) {
            int prevVertex = queue.remove();

            while ((nextVertex = getVertexInMatrix(prevVertex)) != -1) {

                vertexList[nextVertex].setVisited(true);
                int weightEdge = matrix[prevVertex][nextVertex];
                int markerPrevVertex = vertexList[prevVertex].getMarkerVertex();
                int summa = markerPrevVertex + weightEdge;
                int markerNextVertex = vertexList[nextVertex].getMarkerVertex();

                if (summa < markerNextVertex) {
                    vertexList[nextVertex].setMarkerVertex(summa);
                }
                queue.add(nextVertex);
            }

            for (int v = 0; v < vertexList.length; v++) {
                vertexList[v].setVisited(false);
            }
        }
    }

    // проверяем, что к проверяемой вершине есть ребро (т.е вес > 0) и вершина еще не была посещена
    private int getVertexInMatrix(int v) {
        for (int i = 0; i < MAX_VERTEX_SIZE; i++) {
            if (matrix[v][i] > 0 && vertexList[i].getIsVisited() == false) {
                return i;
            }
        }
        return -1;
    }

    // добавить вершины в граф
    public void addVertex(char label) {// задание новых вершин
        vertexList[counterVertex++] = new Vertex(label);
    }

    // матрица смежности
    public void addEdge(int start, int end, int weight) {
        matrix[start][end] = weight; // задание ребер между вершинами, с весом между ними
    }

    // вершина
    public void displayVertex(int v) {
        System.out.println(vertexList[v].getLabel() + " + мин путь  " + vertexList[v].getMarkerVertex());
    }

    public void showGraf() {
        for (int i = 0; i < vertexList.length; i++) {
            displayVertex(i);
        }
    }
}

class Vertex {
    private final int INFINITY = 1_000_000_000;
    private char label;
    private boolean isVisited;
    private int markerVertex;

    public Vertex(char label) {
        this.label = label;
        this.isVisited = false;
        this.markerVertex = INFINITY;
    }

    public int getMarkerVertex() {
        return markerVertex;
    }

    public void setMarkerVertex(int markerVertex) {
        this.markerVertex = markerVertex;
    }

    public char getLabel() {
        return label;
    }

    public void setLabel(char label) {
        this.label = label;
    }

    public boolean getIsVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }
}