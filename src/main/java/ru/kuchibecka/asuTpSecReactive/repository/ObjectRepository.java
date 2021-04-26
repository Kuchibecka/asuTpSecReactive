package ru.kuchibecka.asuTpSecReactive.repository;

import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import org.neo4j.springframework.data.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.kuchibecka.asuTpSecReactive.entity.Object;

@Repository
public interface ObjectRepository extends ReactiveNeo4jRepository<Object, Long> {
    /*
    @Query("MATCH (a:Object) WHERE a.name contains $name RETURN a")
    Flux<Object> findByName(String name);
    */
}
