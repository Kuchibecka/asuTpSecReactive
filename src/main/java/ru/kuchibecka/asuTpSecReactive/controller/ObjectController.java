package ru.kuchibecka.asuTpSecReactive.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kuchibecka.asuTpSecReactive.entity.Object;
import ru.kuchibecka.asuTpSecReactive.entity.SecuritySW;
import ru.kuchibecka.asuTpSecReactive.entity.Virus;
import ru.kuchibecka.asuTpSecReactive.service.ObjectService;
import ru.kuchibecka.asuTpSecReactive.service.SecuritySWService;
import ru.kuchibecka.asuTpSecReactive.service.VirusService;

import java.util.List;


@RestController
@RequestMapping("api/object")
@CrossOrigin(origins = "http://localhost:3000")
public class ObjectController {
    @Autowired
    private ObjectService objectService;

    @Autowired
    private VirusService virusService;

    @Autowired
    private SecuritySWService securitySWService;

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

    @PutMapping("/{id}/edit/")
    Mono<Object> updateObject(@PathVariable Long id, @RequestBody Object object) {
        return objectService.findById(id)
                .flatMap(dbObject -> {
                    BeanUtils.copyProperties(object, dbObject);
                    return objectService.save(dbObject);
                });
    }

    @DeleteMapping("/{id}/delete/")
    Mono<Void> deleteObject(@PathVariable Long id) {
        return objectService.findById(id)
                .flatMap(object ->
                        objectService.delete(object)
                );
    }

    @PutMapping("/{id}/add_virus/{virusId}")
    Mono<Object> addVirusObject(@PathVariable Long id, @PathVariable Long virusId) {
        return objectService.findById(id)
                .flatMap(dbObject -> {
                    List<Virus> newVirusList = dbObject.getVirusList();
                    virusService.findById(virusId)
                            .subscribe(v -> {
                                if (newVirusList.contains(v)) {
                                    return;
                                }
                                newVirusList.add(v);
                                dbObject.setVirusList(newVirusList);
                            });
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/{id}/add_securitysw/{secSwId}")
    Mono<Object> addSecuritySWObject(@PathVariable Long id, @PathVariable Long secSwId) {
        return objectService.findById(id)
                .flatMap(dbObject -> {
                    List<SecuritySW> newSecuritySWList = dbObject.getSecuritySWList();
                    securitySWService.findById(secSwId)
                            .subscribe(v -> {
                                if (newSecuritySWList.contains(v)) {
                                    return;
                                }
                                newSecuritySWList.add(v);
                                dbObject.setSecuritySWList(newSecuritySWList);
                            });
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/{id}/add_object/{objId}")
    Mono<Object> addObjectObject(@PathVariable Long id, @PathVariable Long objId) {
        return objectService.findById(id)
                .flatMap(dbObject -> {
                    List<Object> newObjectList = dbObject.getObjectList();
                    objectService.findById(objId)
                            .subscribe(v -> {
                                if (newObjectList.contains(v)) {
                                    return;
                                }
                                newObjectList.add(v);
                                dbObject.setObjectList(newObjectList);
                            });
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/{id}/add_criteria_object/{objId}")
    Mono<Object> addCriteriaObject(@PathVariable Long id, @PathVariable Long objId) {
        return objectService.findById(id)
                .flatMap(dbObject -> {
                    List<Object> newCriteriaList = dbObject.getAndCriteriaList();
                    objectService.findById(objId)
                            .subscribe(v -> {
                                if (newCriteriaList.contains(v)) {
                                    return;
                                }
                                newCriteriaList.add(v);
                                dbObject.setAndCriteriaList(newCriteriaList);
                            });
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/{id}/remove_virus/{virusId}")
    Mono<Object> removeVirusObject(@PathVariable Long id, @PathVariable Long virusId) {
        return objectService.findById(id)
                .flatMap(dbObject -> {
                    List<Virus> newVirusList = dbObject.getVirusList();
                    virusService.findById(virusId)
                            .subscribe(v -> {
                                if (!newVirusList.contains(v)) {
                                    return;
                                }
                                newVirusList.remove(v);
                                dbObject.setVirusList(newVirusList);
                            });
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/{id}/remove_securitysw/{secSwId}")
    Mono<Object> removeSecuritySWObject(@PathVariable Long id, @PathVariable Long secSwId) {
        return objectService.findById(id)
                .flatMap(dbObject -> {
                    List<SecuritySW> newSecuritySWList = dbObject.getSecuritySWList();
                    securitySWService.findById(secSwId)
                            .subscribe(v -> {
                                if (!newSecuritySWList.contains(v)) {
                                    return;
                                }
                                newSecuritySWList.remove(v);
                                dbObject.setSecuritySWList(newSecuritySWList);
                            });
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/{id}/remove_object/{objId}")
    Mono<Object> removeObjectObject(@PathVariable Long id, @PathVariable Long objId) {
        return objectService.findById(id)
                .flatMap(dbObject -> {
                    List<Object> newObjectList = dbObject.getObjectList();
                    objectService.findById(objId)
                            .subscribe(v -> {
                                if (!newObjectList.contains(v)) {
                                    return;
                                }
                                newObjectList.remove(v);
                                dbObject.setObjectList(newObjectList);
                            });
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/{id}/remove_criteria_object/{objId}")
    Mono<Object> removeCriteriaObject(@PathVariable Long id, @PathVariable Long objId) {
        return objectService.findById(id)
                .flatMap(dbObject -> {
                    List<Object> newCriteriaList = dbObject.getAndCriteriaList();
                    objectService.findById(objId)
                            .subscribe(v -> {
                                if (!newCriteriaList.contains(v)) {
                                    return;
                                }
                                newCriteriaList.remove(v);
                                dbObject.setAndCriteriaList(newCriteriaList);
                            });
                    return objectService.save(dbObject);
                });
    }

    @PostMapping("/new_instance/{id}")
    Mono<Object> createObjectInstance(@PathVariable Long id) {
        return objectService.findById(id)
                .flatMap(obj -> {
                    Object instance = new Object();
                    BeanUtils.copyProperties(obj, instance, "obj_id");
                    instance.setIsInstance(true);
                    return objectService.save(instance);
                });

    }
}
