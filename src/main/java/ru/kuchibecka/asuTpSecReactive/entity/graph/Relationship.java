package ru.kuchibecka.asuTpSecReactive.entity.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Relationship {
    private String id;

    private String source;

    private String target;

    private String type;

    private boolean animated;

    private String label;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Style {
        private String stroke;
    }

    private Style style;
}
