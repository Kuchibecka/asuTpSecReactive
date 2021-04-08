package ru.kuchibecka.asuTpSecReactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kuchibecka.asuTpSecReactive.entity.Object;
import ru.kuchibecka.asuTpSecReactive.repository.ObjectRepository;

@Service
public class ObjectService {
    @Autowired
    ObjectRepository objectRepository;

    public Flux<Object> getObjectByName(final String name) {
        return objectRepository
                .findAll()
                .filter(object -> object.getName().contains(name));
    }

    public Flux<Object> findAll() {
        return objectRepository.findAll();
    }

    public Mono<Object> findById(Long id) {
        return objectRepository.findById(id);
    }
}
