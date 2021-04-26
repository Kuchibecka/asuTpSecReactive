package ru.kuchibecka.asuTpSecReactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kuchibecka.asuTpSecReactive.entity.Virus;
import ru.kuchibecka.asuTpSecReactive.repository.VirusRepository;

@Service
public class VirusService {
    @Autowired
    VirusRepository virusRepository;

    public Flux<Virus> getVirusByName(final String name) {
        return virusRepository
                .findAll()
                .filter(object -> object.getName().contains(name));
    }

    public Flux<Virus> findAll() {
        return virusRepository.findAll();
    }

    public Mono<Virus> findById(Long id) {
        return virusRepository.findById(id);
    }

    public Mono<Virus> save(Virus virus) {
        return virusRepository.save(virus);
    }

    public Mono<Void> delete(Virus virus) {
        return virusRepository.delete(virus);
    }
}
