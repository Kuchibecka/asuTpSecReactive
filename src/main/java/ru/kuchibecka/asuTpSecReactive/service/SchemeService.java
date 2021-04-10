package ru.kuchibecka.asuTpSecReactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kuchibecka.asuTpSecReactive.entity.Node;
import ru.kuchibecka.asuTpSecReactive.entity.Object;
import ru.kuchibecka.asuTpSecReactive.entity.Scheme;
import ru.kuchibecka.asuTpSecReactive.repository.SchemeRepository;

import java.util.Collection;
import java.util.List;

@Service
public class SchemeService {
    @Autowired
    SchemeRepository schemeRepository;

    public Flux<Scheme> getObjectByName(String name) {
        return schemeRepository
                .findAll()
                .filter(scheme -> scheme.getName().contains(name));
    }

    public Flux<Scheme> findAll() {
        return schemeRepository.findAll();
    }

    public Mono<Scheme> findById(Long id) {
        return schemeRepository.findById(id);
    }

    /*
    public Flux<Node> findNodesById(Long id) {
        return schemeRepository.findNodesById(id);
    }
     */
}
