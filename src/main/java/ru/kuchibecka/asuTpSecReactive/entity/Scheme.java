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
public class Scheme {
    @Getter @Setter
    @Id
    private Long scheme_id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String description;

    @Getter @Setter
    @Relationship(type = "CONSISTS_OF", direction = Relationship.Direction.OUTGOING)
    private List<Object> objectList = new ArrayList<>();

    @Relationship(type = "CONTAINS", direction = Relationship.Direction.OUTGOING)
    private List<Virus> virusList = new ArrayList<>();

    @Relationship(type = "FAILS_AT", direction = Relationship.Direction.OUTGOING)
    private List<Object> criteriaList = new ArrayList<>();
}
//todo: написать оснастку (репо, сервис, контроллер)