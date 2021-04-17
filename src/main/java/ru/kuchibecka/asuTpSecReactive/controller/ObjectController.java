package ru.kuchibecka.asuTpSecReactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kuchibecka.asuTpSecReactive.repository.ObjectRepository;
import ru.kuchibecka.asuTpSecReactive.entity.Object;
import ru.kuchibecka.asuTpSecReactive.service.ObjectService;

import java.util.ArrayList;

@RestController
@RequestMapping("api/object")
@CrossOrigin("*")
public class ObjectController {
    @Autowired
    private ObjectService objectService;

    // todo: убрать /*
    @Autowired
    private ObjectRepository objectRepository;
    // todo: убрать, реализовав save() в objectService */

    @GetMapping(path = "")
    Flux<Object> getObjects() {
        return objectService.findAll();
    }

    //@GetMapping(path = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @GetMapping("/{id}")
    Mono<Object> getById(@PathVariable Long id) {
        return objectService.findById(id);
    }

    @GetMapping("/by-name")
    Flux<Object> byName(@RequestParam("name") String name) {
        System.out.println(name);
        return objectService.getObjectByName(name);
    }

    // возможно post-запрос с property класса Object передаваемыми queryParams
    @PostMapping(value ="/new", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    Mono<Object> createObject(@RequestBody Object object) {
        System.out.println(object);
        return objectRepository.save(object);
    }
}
