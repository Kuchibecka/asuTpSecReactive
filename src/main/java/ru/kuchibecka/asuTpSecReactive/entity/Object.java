package ru.kuchibecka.asuTpSecReactive.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.springframework.data.core.schema.Id;
import org.neo4j.springframework.data.core.schema.Node;
import org.neo4j.springframework.data.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Node
public class Object {
    @Getter @Setter
    @Id // ? @GeneratedValue ?
    private Long obj_id;

    @Getter @Setter
    private int type;

    @Getter @Setter
    private String name;

    @Relationship(type = "INFECTED_BY", direction = Relationship.Direction.OUTGOING)
    private List<Virus> virusList = new ArrayList<>();

    @Relationship(type = "PROTECTED_BY", direction = Relationship.Direction.OUTGOING)
    private List<SecuritySW> securitySWList = new ArrayList<>();

    @Relationship(type = "CONNECTED_TO", direction = Relationship.Direction.OUTGOING)
    private List<Object> objectList = new ArrayList<>();

    @Relationship(type = "AND", direction = Relationship.Direction.OUTGOING)
    private List<Object> andCriteriaList = new ArrayList<>();
}
//todo: проверить на полноту оснастку (репо, сервис, контроллер)