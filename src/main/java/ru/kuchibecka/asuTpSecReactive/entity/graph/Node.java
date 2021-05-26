package ru.kuchibecka.asuTpSecReactive.entity.graph;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Node {
    private String id;

    private String type;

    private Map<String, String> style;

    @Data
    @AllArgsConstructor
    public static class data {
        private String label;
    }
    private data data;

    @Data
    @AllArgsConstructor
    public static class position {
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
        this.style = new HashMap();
        this.style.put("border", "1.1mm solid");
        this.style.put("border-radius", "16px");
        if (id.startsWith("virus")) {
            this.type = "input";
            this.style.put("background", "#eb9696");
        } else {
            if (id.startsWith("securitySW")){
                this.type = "input";
                this.style.put("background", "#bbbbff");
            }
            else {
                this.type = "default";
                this.style.put("background", "#dddddd");
            }
        }
    }

    public Node(String id, String data, int x, int y) {
        this.id = id;
        this.position = new position();
        this.data = new data(data);
        this.position = new position(x, y);
        if (this.id.matches("\\d*")) {
            this.type = "output";
        }
    }
}
