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
public class SecuritySW {
    @Id @GeneratedValue
    private Long secSW_id;

    private String name;

    private int price;

    private String description;

    @Relationship(type = "PREVENTS_EXPLOIT", direction = Relationship.Direction.OUTGOING)
    private List<Exploit> securityExploit = new ArrayList<>();
}
//todo: написать оснастку (репо, сервис, контроллер)