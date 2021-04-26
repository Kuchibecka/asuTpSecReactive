package ru.kuchibecka.asuTpSecReactive.repository;

import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;
import ru.kuchibecka.asuTpSecReactive.entity.SecuritySW;

@Repository
public interface SecuritySWRepository extends ReactiveNeo4jRepository<SecuritySW, Long> {
}
