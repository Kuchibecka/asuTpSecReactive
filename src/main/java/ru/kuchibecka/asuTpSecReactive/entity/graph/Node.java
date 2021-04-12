package ru.kuchibecka.asuTpSecReactive.entity.graph;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Node {
    private String id;

    @Data
    public class data {
        private String label;
    }
    private data data;

    @Data
    public class position {
        private int x;
        private int y;

        public position() {
            this.x = (int) (100 + Math.random()*100);
            this.y = (int) (100 + Math.random()*100);
        }
    }
    private position position;

    // constructor for label assignment
    public Node(String id, String data) {
        this.id = id;
        this.position = new position();
        Node.data data1 = new data();
        data1.label = data;
        this.data = data1;
    }
    //todo: Другие поля класса Object?
}
