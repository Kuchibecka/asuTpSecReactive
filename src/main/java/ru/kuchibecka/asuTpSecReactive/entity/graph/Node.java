package ru.kuchibecka.asuTpSecReactive.entity.graph;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Node {
    private String id;

    @Data
    @AllArgsConstructor
    public class data {
        private String label;
    }
    private data data;

    @Data
    @AllArgsConstructor
    public class position {
        private int x;
        private int y;

        public position() {
            // double spread = Math.random()*4 - 2;
            this.x = (int) (100 + Math.random()*150);
            this.y = (int) (100 + Math.random()*150);
        }
    }
    private position position;

    // constructor for label assignment
    public Node(String id, String data) {
        this.id = id;
        this.position = new position();
        this.data = new data(data);
    }

    public Node(String id, String data, int x, int y) {
        this.id = id;
        this.position = new position();
        this.data = new data(data);
        this.position = new position(x, y);
    }
}
