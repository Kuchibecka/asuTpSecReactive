package ru.kuchibecka.asuTpSecReactive.entity.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.springframework.data.core.schema.Node;

import java.util.Map;

// {id: "e1-5", source: "5", target: "2", type: "step", animated: true}

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Relationship {
    private String id;

    private Long source;

    private Long target;

    private String type;

    private boolean animated;
}