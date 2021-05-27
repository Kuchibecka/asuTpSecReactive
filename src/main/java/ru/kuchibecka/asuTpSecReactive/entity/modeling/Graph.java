package ru.kuchibecka.asuTpSecReactive.entity.modeling;

import lombok.Data;

import java.util.*;

@Data
public class Graph {
    private ArrayList<Vertex> vertexList; //массив вершин
    private int[][] adjMat; // матрица смежности todo: что-то сделать с инициализацией
    private int nVertexes; // текущее количество вершин
    private Queue<Integer> queue = new ArrayDeque<>();
    private ArrayList<ArrayList<Map<Integer, Boolean>>> treeAndRelations;
    private ArrayList<Integer> treeOrRelations;

    public Graph(ArrayList<Vertex> vertexList, int[][] adjMat,
                 ArrayList<ArrayList<Integer>> treeAndRelations,
                 ArrayList<Integer> treeOrRelations) {
        this.adjMat = adjMat;
        this.vertexList = vertexList;
        this.nVertexes = vertexList.size();
        this.treeAndRelations = new ArrayList<>();
        for (ArrayList<Integer> and : treeAndRelations) {
            Map<Integer, Boolean> infectionMap = new HashMap<>();
            for (Integer j : and) {
                infectionMap.put(j, false);
            }
            ArrayList<Map<Integer, Boolean>> mapAndList = new ArrayList<>();
            mapAndList.add(infectionMap);
            this.treeAndRelations.add(mapAndList);
        }
        this.treeOrRelations = treeOrRelations;
    }

    private int getUnvisitedVertex(int v) {
        for (int j = 0; j < nVertexes; j++) {
            if (adjMat[v][j] == 1 && !vertexList.get(j).isVisited()) {
                return j;
            }
        }
        return -1;
    }

    //todo:
    // используется обход вширь потому что:
    // 1) Это оответствует модели распространения самореплицируещегося вируса;
    // 2) Это позволяет ускорить алгоритм засчёт
    // todo: проверки на падение дерева
    //  (каждый раз, когда заражается объект, идёт проверка на критичность для дерева
    //  если в соотв с деревом отказа система уже отказала, рез-тат сразу выводится,
    //  а моделирование останавливается)
    //
    public void bfc() {
        Boolean result = true;
        ArrayList<Vertex> initiallyInfected = new ArrayList<>();
        for (Vertex v : vertexList) {
            System.out.println(v.isInfected());
            if (v.isInfected()) {
                initiallyInfected.add(v);
            }
        }
        for (Vertex v : initiallyInfected) {
            // обход
            v.setVisited(true);
            System.out.println("Starting from: " + v.getId());
            queue.add(v.getId());
            System.out.println("Added to queue: " + v.getId());

            while (!queue.isEmpty()) {
                int v1 = queue.remove();
                System.out.println("Removed from queue: " + v1);
                int nextVertex;
                while ((nextVertex = getUnvisitedVertex(v1)) != -1) {
                    Vertex currentVertex = vertexList.get(nextVertex);
                    currentVertex.setVisited(true);
                    System.out.println("Посетил: " + currentVertex.getId());
                    if (!currentVertex.getSecurityExploits().containsAll(v.getVirusExploits())) {
                        currentVertex.setInfected(true);
                        if (this.treeOrRelations.contains(currentVertex.getId())) {
                            result = false;
                            System.out.println("Завершение работы! Or-условие для id: " + currentVertex.getId() + "!");
                            break;
                            // return result;
                        } else {
                            // проверка дерева отказа на случай
                            //          быстрого завершения при падении системы
                            for (ArrayList<Map<Integer, Boolean>> andRel : this.treeAndRelations) {
                                for (Map<Integer, Boolean> j : andRel) {
                                    if (j.get(currentVertex.getId())) {
                                        j.put(currentVertex.getId(), true);
                                    }
                                    if (!j.containsValue(false)) {
                                        result = false;
                                        System.out.println("Завершение работы! And-условие для: " + andRel + "!");
                                        break;
                                        // return result;
                                    }
                                }
                            }
                        }
                        // todo: чек дерева отказа
                        queue.add(nextVertex);
                    }
                    System.out.println("Следующий на очереди: " + nextVertex);
                }
            }

            // сброс флагов обхода у вершин для этого вируса
            for (Vertex vertex : vertexList) {
                vertex.setVisited(false);
            }
        }
        for (Vertex v : vertexList) {
            System.out.println(v.getId() + " isInfected: " + v.isInfected());
        }
        // return result;
    }
}
