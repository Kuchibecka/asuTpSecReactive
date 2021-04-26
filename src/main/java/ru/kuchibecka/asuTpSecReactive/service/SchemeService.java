package ru.kuchibecka.asuTpSecReactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kuchibecka.asuTpSecReactive.entity.Object;
import ru.kuchibecka.asuTpSecReactive.entity.Scheme;
import ru.kuchibecka.asuTpSecReactive.repository.SchemeRepository;


@Service
public class SchemeService {
    @Autowired
    SchemeRepository schemeRepository;

    public Flux<Scheme> getSchemeByName(String name) {
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

    public Mono<Scheme> save(Scheme scheme) {
        return schemeRepository.save(scheme);
    }
}
