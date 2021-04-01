package ru.kuchibecka.asuTpSecReactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kuchibecka.asuTpSecReactive.dao.ObjectRepository;
import ru.kuchibecka.asuTpSecReactive.entity.Object;

@RestController
@RequestMapping("api/object/")
public class ObjectController {
    @Autowired
    private ObjectRepository objectRepository;


    @GetMapping(path = "")
    Flux<Object> getObjects() {
        return objectRepository.findAll();
    }

    //@GetMapping(path = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @GetMapping("/{id}")
    Mono<Object> getById(@PathVariable Long id) {
        return objectRepository.findById(id);
    }
}
