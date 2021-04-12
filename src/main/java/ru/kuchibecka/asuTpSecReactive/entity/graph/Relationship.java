package ru.kuchibecka.asuTpSecReactive.entity.graph;

import lombok.AllArgsConstructor;
import lombok.Data;

// {id: "e1-5", source: "5", target: "2", type: "step", animated: true}

@Data
@AllArgsConstructor
public class Relationship {
    private int startId;

    private int endId;
/*
    private String end;

    private String type;

    private boolean animated;
*/
}
