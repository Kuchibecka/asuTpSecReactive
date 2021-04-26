package ru.kuchibecka.asuTpSecReactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kuchibecka.asuTpSecReactive.entity.SecuritySW;
import ru.kuchibecka.asuTpSecReactive.repository.SecuritySWRepository;

@Service
public class SecuritySWService {
    @Autowired
    SecuritySWRepository securitySWRepository;

    public Flux<SecuritySW> getSecuritySWByName(final String name) {
        return securitySWRepository
                .findAll()
                .filter(object -> object.getName().contains(name));
    }

    public Flux<SecuritySW> findAll() {
        return securitySWRepository.findAll();
    }

    public Mono<SecuritySW> findById(Long id) {
        return securitySWRepository.findById(id);
    }

    public Mono<SecuritySW> save(SecuritySW securitySW) {
        return securitySWRepository.save(securitySW);
    }

    public Mono<Void> delete(SecuritySW securitySW) {
        return securitySWRepository.delete(securitySW);
    }
}
