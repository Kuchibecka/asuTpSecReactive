package ru.kuchibecka.asuTpSecReactive.entity.modeling;

import lombok.Data;

import java.util.*;

@Data
public class Graph {
    private ArrayList<Vertex> vertexList; // массив вершин
    private int[][] adjMat; // матрица смежности todo: инициализация исправлена, выделяется
    //                                                  столько памяти сколько нужно
    private int nVertexes; // текущее количество вершин
    private Queue<Integer> queue = new ArrayDeque<>();
    private ArrayList<ArrayList<Map<Integer, Boolean>>> treeAndRelations; // для хранения and-соотношений
    private ArrayList<Integer> treeOrRelations; // для хранения or-соотношений

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
        System.out.println("Tree and relations: " + this.treeAndRelations);
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
    //  проверки на падение дерева
    //  (каждый раз, когда заражается объект, идёт проверка на критичность для дерева
    //  если в соотв с деревом отказа система уже отказала, рез-тат сразу выводится,
    //  а моделирование останавливается)
    //
    public Boolean bfc() {
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
            System.out.println("Начало зааржения от: " + v.getId());
            queue.add(v.getId());

            while (!queue.isEmpty()) {
                int v1 = queue.remove();
                int nextVertex;
                while ((nextVertex = getUnvisitedVertex(v1)) != -1) {
                    Vertex currentVertex = vertexList.get(nextVertex);
                    currentVertex.setVisited(true);
                    System.out.println("Вирус распространился на: " + currentVertex.getId());
                    if (!currentVertex.getSecurityExploits().containsAll(v.getVirusExploits())) {
                        currentVertex.setInfected(true);
                        // проверка дерева отказа на случай
                        //          незамедлительного завершения при падении системы (or-условие отказа)
                        if (this.treeOrRelations.contains(currentVertex.getId())) {
                            System.out.println("Завершение работы! Or-условие для id: " + currentVertex.getId() + "!");
                            return false;
                        } else {
                            // проверка на наличие заражённого объекта в and-условиях отказа
                            for (ArrayList<Map<Integer, Boolean>> andRel : this.treeAndRelations) {
                                for (Map<Integer, Boolean> j : andRel) {
                                    if (j.containsKey(currentVertex.getId())) {
                                        if (!j.get(currentVertex.getId())) {
                                            System.out.println("Заразил из И-соотношения: " + currentVertex.getId() + "!");
                                            // отметка о выполнении одной из ветвей and-условия отказа
                                            j.put(currentVertex.getId(), true);
                                        }
                                    }
                                    // проверка дерева отказа на случай
                                    //          незамедлительного завершения при падении системы (and-условие)
                                    if (!j.containsValue(false)) {
                                        System.out.println("Завершение работы! And-условие для: " + andRel + "!");
                                        return false;
                                    }
                                }
                            }
                        }
                        queue.add(nextVertex);
                    }
                }
            }
            // сброс флагов обхода у вершин для этого вируса
            for (Vertex vertex : vertexList) {
                vertex.setVisited(false);
            }
        }
        /*for (Vertex v : vertexList) {
            System.out.println(v.getId() + " isInfected: " + v.isInfected());
        }*/
        return true;
    }
}
