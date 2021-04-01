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
public class Virus {
    @Getter @Setter
    @Id
    private Long virus_id;

    @Getter @Setter
    private String name;

    @Relationship(type = "HAS_EXPLOITS", direction = Relationship.Direction.OUTGOING)
    private List<VirusExploit> virusExploit = new ArrayList<>();
}
//todo: написать оснастку (репо, сервис, контроллер)