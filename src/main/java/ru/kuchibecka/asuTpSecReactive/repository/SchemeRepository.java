package ru.kuchibecka.asuTpSecReactive.repository;

import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import org.neo4j.springframework.data.repository.query.Query;
import reactor.core.publisher.Mono;
import ru.kuchibecka.asuTpSecReactive.entity.Node;
import ru.kuchibecka.asuTpSecReactive.entity.Scheme;
import ru.kuchibecka.asuTpSecReactive.service.SchemeService;

import java.util.List;

public interface SchemeRepository extends ReactiveNeo4jRepository<Scheme, Long> {
    @Query("MATCH (s:Scheme)-[con:CONSISTS_OF]->(nod1:Object)-[rel:CONNECTED_TO*]-(nod2:Object) WHERE (s.scheme_id=$id) RETURN DISTINCT nod1;")
    Mono<List<Node>> findNodesById(Long id);
}
