package ru.kuchibecka.asuTpSecReactive.repository;

import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import org.neo4j.springframework.data.repository.query.Query;
import reactor.core.publisher.Flux;
import ru.kuchibecka.asuTpSecReactive.entity.Object;

public interface ObjectRepository extends ReactiveNeo4jRepository<Object, Long> {
    /*
    @Query("MATCH (a:Object) WHERE a.name contains $name RETURN a")
    Flux<Object> findByName(String name);
    */
}
