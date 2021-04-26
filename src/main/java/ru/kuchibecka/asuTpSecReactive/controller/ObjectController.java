package ru.kuchibecka.asuTpSecReactive.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kuchibecka.asuTpSecReactive.entity.Object;
import ru.kuchibecka.asuTpSecReactive.entity.SecuritySW;
import ru.kuchibecka.asuTpSecReactive.entity.Virus;
import ru.kuchibecka.asuTpSecReactive.repository.ObjectRepository;
import ru.kuchibecka.asuTpSecReactive.service.ObjectService;

import java.util.List;


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

    @PutMapping("/add_virus/{id}")
    Mono<Object> addVirusObject(@PathVariable Long id, @RequestBody Virus virus) {
        return objectRepository.findById(id)
                .flatMap(dbObject -> {
                    List<Virus> newVirusList = dbObject.getVirusList();
                    newVirusList.add(virus);
                    dbObject.setVirusList(newVirusList);
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/add_securitysw/{id}")
    Mono<Object> addSecuritySWObject(@PathVariable Long id, @RequestBody SecuritySW securitySW) {
        return objectRepository.findById(id)
                .flatMap(dbObject -> {
                    List<SecuritySW> newSecuritySWList = dbObject.getSecuritySWList();
                    newSecuritySWList.add(securitySW);
                    dbObject.setSecuritySWList(newSecuritySWList);
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/add_object/{id}")
    Mono<Object> addObjectObject(@PathVariable Long id, @RequestBody Object object) {
        return objectRepository.findById(id)
                .flatMap(dbObject -> {
                    List<Object> newObjectList = dbObject.getObjectList();
                    newObjectList.add(object);
                    dbObject.setObjectList(newObjectList);
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/add_criteria_object/{id}")
    Mono<Object> addCriteriaObject(@PathVariable Long id, @RequestBody Object object) {
        return objectRepository.findById(id)
                .flatMap(dbObject -> {
                    List<Object> newCriteriaList = dbObject.getAndCriteriaList();
                    newCriteriaList.add(object);
                    dbObject.setAndCriteriaList(newCriteriaList);
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/remove_virus/{id}")
    Mono<Object> removeVirusObject(@PathVariable Long id, @RequestBody Virus virus) {
        return objectRepository.findById(id)
                .flatMap(dbObject -> {
                    List<Virus> newVirusList = dbObject.getVirusList();
                    newVirusList.remove(virus);
                    dbObject.setVirusList(newVirusList);
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/remove_securitysw/{id}")
    Mono<Object> removeSecuritySWObject(@PathVariable Long id, @RequestBody SecuritySW securitySW) {
        return objectRepository.findById(id)
                .flatMap(dbObject -> {
                    List<SecuritySW> newSecuritySWList = dbObject.getSecuritySWList();
                    newSecuritySWList.remove(securitySW);
                    dbObject.setSecuritySWList(newSecuritySWList);
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/remove_object/{id}")
    Mono<Object> removeObjectObject(@PathVariable Long id, @RequestBody Object object) {
        return objectRepository.findById(id)
                .flatMap(dbObject -> {
                    List<Object> newObjectList = dbObject.getObjectList();
                    newObjectList.remove(object);
                    dbObject.setObjectList(newObjectList);
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/remove_criteria_object/{id}")
    Mono<Object> removeCriteriaObject(@PathVariable Long id, @RequestBody Object object) {
        return objectRepository.findById(id)
                .flatMap(dbObject -> {
                    List<Object> newCriteriaList = dbObject.getAndCriteriaList();
                    newCriteriaList.remove(object);
                    dbObject.setAndCriteriaList(newCriteriaList);
                    return objectService.save(dbObject);
                });
    }

    // todo: put-mapping: 1) Связать объекты просто и and-связью в дереве отказов; 2) Связать объект с SecuritySW; 3) Связать объект с Virus
    // todo: put-mapping: Удалить всё вышеперечисленное
}
