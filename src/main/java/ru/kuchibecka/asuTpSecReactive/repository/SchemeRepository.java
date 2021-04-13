package ru.kuchibecka.asuTpSecReactive.repository;

import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;
import ru.kuchibecka.asuTpSecReactive.entity.Scheme;

@Repository
public interface SchemeRepository extends ReactiveNeo4jRepository<Scheme, Long> {
    /*
    @Query("MATCH (s:Scheme)-[con:CONSISTS_OF]->(nod1:Object)-[rel:CONNECTED_TO*]-(nod2:Object) WHERE (s.scheme_id=$id) RETURN DISTINCT nod1;")
    Flux<Node> findNodesById(Long id);
    */
/*
    @Query("MATCH (s:Scheme)-[:CONSISTS_OF]->(n1:Object), c=(n1)-[con:CONNECTED_TO]-(:Object) " +
            "WHERE (s.scheme_id=$id) " +
            "RETURN collect(DISTINCT {startId: id(startNode(con)), endId: id(endNode(con))}) AS rels")
    Flux<ListValue> findRelationsById(Long id);

 */
}
