package ru.kuchibecka.asuTpSecReactive.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kuchibecka.asuTpSecReactive.entity.Object;
import ru.kuchibecka.asuTpSecReactive.repository.ObjectRepository;
import ru.kuchibecka.asuTpSecReactive.service.ObjectService;


@RestController
@RequestMapping("api/object")
@CrossOrigin(origins = "http://localhost:3000")
public class ObjectController {
    @Autowired
    private ObjectService objectService;

    @Autowired
    private ObjectRepository objectRepository;

    @GetMapping(path = "")
    Flux<Object> getObjects() {
        return objectService.findAll();
    }

    @GetMapping("/{id}")
    Mono<Object> getById(@PathVariable Long id) {
        return objectService.findById(id);
    }

    @GetMapping("/by-name")
    Flux<Object> byName(@RequestParam("name") String name) {
        return objectService.getObjectByName(name);
    }

    @PostMapping("")
    Mono<Object> createObject(@RequestBody Object object) {
        return objectService.save(object);
    }

    @PutMapping("/edit/{id}")
    Mono<Object> updateObject(@PathVariable Long id, @RequestBody Object object) {
        return objectRepository.findById(id)
                .flatMap(dbObject -> {
                    BeanUtils.copyProperties(object, dbObject);
                    return objectService.save(dbObject);
                });
    }

    @DeleteMapping("/delete/{id}")
    Mono<Boolean> deleteObject(@PathVariable Long id) {
        return objectService.findById(id)
                .flatMap(object ->
                        objectRepository.delete(object)
                                .then(Mono.just(true))
                );
    }
}
