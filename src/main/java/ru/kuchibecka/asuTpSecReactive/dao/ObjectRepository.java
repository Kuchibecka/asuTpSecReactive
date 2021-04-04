package ru.kuchibecka.asuTpSecReactive.dao;

import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import ru.kuchibecka.asuTpSecReactive.entity.Object;

public interface ObjectRepository extends ReactiveNeo4jRepository<Object, Long> {

}
