package ru.kuchibecka.asuTpSecReactive.entity.modeling;

import lombok.Data;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

@Data
public class Graph {
    private ArrayList<Vertex> vertexList = new ArrayList<>(); //массив вершин
    private int[][] adjMat = new int[50][50]; // матрица смежности todo: что-то сделать с инициализацией
    private int nVertexes; // текущее количество вершин
    private Queue<Integer> queue = new ArrayDeque<>();

    public void addVertex(Vertex vertex) {
        vertexList.add(vertex);
        nVertexes++;
    }

    public void addEdge(int source, int target) {
        adjMat[source][target] = 1;
        adjMat[target][source] = 1;
    }

    private int getUnvisitedVertex(int v) {
        for (int j = 0; j < nVertexes; j++) {
            if (adjMat[v][j] == 1 && !vertexList.get(j).isVisited()) {
                return j;
            }
        }
        return -1;
    }

    public void bfc() {
        ArrayList<Vertex> initiallyInfected = new ArrayList<>();
        for (Vertex v : vertexList) {
            if (v.isInfected()) {
                initiallyInfected.add(v);
            }
        }
        for (Vertex v : initiallyInfected) {
            // обход
            v.setVisited(true);
            System.out.println("Visited: " + v);
            queue.add(Math.toIntExact(v.getId()));

            while (!queue.isEmpty()) {
                int v1 = Math.toIntExact(queue.remove());
                for (int i = 0; i < vertexList.size(); i++) {
                    if (vertexList.get(i).getId() == v1) {
                        v1 = i;
                    }
                }
                Vertex spreading = vertexList.get(v1);
                int nextVertex = getUnvisitedVertex(v1);
                if (nextVertex != -1) {
                    for (int i = 0; i < vertexList.size(); i++) {
                        if (vertexList.get(i).getId() == nextVertex) {
                            nextVertex = i;
                        }
                    }
                }
                while (nextVertex != -1) {
                    Vertex currentVertex = vertexList.get(nextVertex);
                    currentVertex.setVisited(true);
                    System.out.println("Also visited: " + currentVertex);
                    if (!currentVertex.getSecurityExploits().containsAll(spreading.getVirusExploits())) {
                        currentVertex.setInfected(true);
                        nextVertex = getUnvisitedVertex(v1);
                    } else {
                        nextVertex = -1;
                    }
                    queue.add(nextVertex);
                }
            }

            //todo: сброс флагов обхода для этого вируса
        }
    }
}
