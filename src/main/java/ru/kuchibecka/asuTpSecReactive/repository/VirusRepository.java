package ru.kuchibecka.asuTpSecReactive.repository;

import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;
import ru.kuchibecka.asuTpSecReactive.entity.Virus;

@Repository
public interface VirusRepository extends ReactiveNeo4jRepository<Virus, Long> {
}
