package ru.kuchibecka.asuTpSecReactive.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.springframework.data.core.schema.GeneratedValue;
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
    @Id @GeneratedValue
    private Long obj_id;

    private int type;

    private String name;

    @Relationship(type = "INFECTED_BY", direction = Relationship.Direction.OUTGOING)
    private List<Virus> virusList = new ArrayList<>();

    @Relationship(type = "PROTECTED_BY", direction = Relationship.Direction.OUTGOING)
    private List<SecuritySW> securitySWList = new ArrayList<>();

    @Relationship(type = "CONNECTED_TO", direction = Relationship.Direction.OUTGOING)
    private List<Object> objectList = new ArrayList<>();

    @Relationship(type = "AND", direction = Relationship.Direction.OUTGOING)
    private List<Object> andCriteriaList = new ArrayList<>();

    public Object(int type, String name, List<Virus> virusList, List<SecuritySW> securitySWList, List<Object> objectList, List<Object> andCriteriaList) {
        this.type = type;
        this.name = name;
        this.virusList = virusList;
        this.securitySWList = securitySWList;
        this.objectList = objectList;
        this.andCriteriaList = andCriteriaList;
    }
}
//todo: проверить на полноту оснастку (репо, сервис, контроллер)