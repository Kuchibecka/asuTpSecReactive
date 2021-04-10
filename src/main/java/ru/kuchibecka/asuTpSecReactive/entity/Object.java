package ru.kuchibecka.asuTpSecReactive.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.driver.internal.shaded.reactor.util.annotation.Nullable;
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

    @Nullable
    @Relationship(type = "INFECTED_BY", direction = Relationship.Direction.OUTGOING)
    private List<Virus> virusList = new ArrayList<>();

    @Nullable
    @Relationship(type = "PROTECTED_BY", direction = Relationship.Direction.OUTGOING)
    private List<SecuritySW> securitySWList = new ArrayList<>();

    @Nullable
    @Relationship(type = "CONNECTED_TO", direction = Relationship.Direction.OUTGOING)
    private List<Object> objectList = new ArrayList<>();

    @Nullable
    @Relationship(type = "AND", direction = Relationship.Direction.OUTGOING)
    private List<Object> andCriteriaList = new ArrayList<>();

    public Object(Long obj_id, int type, String name) {
        List<Virus> virusList = new ArrayList<>();
        List<SecuritySW> securitySWList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();
        List<Object> andCriteriaList = new ArrayList<>();
        Object object = new Object(obj_id, type, name, virusList, securitySWList, objectList, andCriteriaList);
    }
}
//todo: проверить на полноту оснастку (репо, сервис, контроллер)