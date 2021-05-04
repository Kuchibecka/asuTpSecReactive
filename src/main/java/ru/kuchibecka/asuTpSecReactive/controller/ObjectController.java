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

    @PutMapping("/{id}/add_virus/")
    Mono<Object> addVirusObject(@PathVariable Long id, @RequestBody Virus virus) {
        return objectService.findById(id)
                .flatMap(dbObject -> {
                    List<Virus> newVirusList = dbObject.getVirusList();
                    newVirusList.add(virus);
                    dbObject.setVirusList(newVirusList);
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/{id}/add_securitysw/")
    Mono<Object> addSecuritySWObject(@PathVariable Long id, @RequestBody SecuritySW securitySW) {
        return objectService.findById(id)
                .flatMap(dbObject -> {
                    List<SecuritySW> newSecuritySWList = dbObject.getSecuritySWList();
                    newSecuritySWList.add(securitySW);
                    dbObject.setSecuritySWList(newSecuritySWList);
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/{id}/add_object/")
    Mono<Object> addObjectObject(@PathVariable Long id, @RequestBody Object object) {
        return objectService.findById(id)
                .flatMap(dbObject -> {
                    List<Object> newObjectList = dbObject.getObjectList();
                    newObjectList.add(object);
                    dbObject.setObjectList(newObjectList);
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/{id}/add_criteria_object/")
    Mono<Object> addCriteriaObject(@PathVariable Long id, @RequestBody Object object) {
        return objectService.findById(id)
                .flatMap(dbObject -> {
                    List<Object> andCriteriaList = dbObject.getAndCriteriaList();
                    andCriteriaList.add(object);
                    dbObject.setAndCriteriaList(andCriteriaList);
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/{id}/remove_virus/{virusId}")
    Mono<Object> removeVirusObject(@PathVariable Long id, @PathVariable Long virusId) {
        return objectService.findById(id)
                .flatMap(dbObject -> {
                    List<Virus> newVirusList = dbObject.getVirusList();
                    int l = 0;
                    for (Virus o : newVirusList) {
                        if (o.getVirus_id().equals(virusId)) {
                            break;
                        } else {
                            l++;
                        }
                    }
                    newVirusList.remove(l);
                    dbObject.setVirusList(newVirusList);
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/{id}/remove_securitysw/{secSwId}")
    Mono<Object> removeSecuritySWObject(@PathVariable Long id, @PathVariable Long secSwId) {
        return objectService.findById(id)
                .flatMap(dbObject -> {
                    List<SecuritySW> newSecuritySWList = dbObject.getSecuritySWList();
                    int l = 0;
                    for (SecuritySW o : newSecuritySWList) {
                        if (o.getSecSW_id().equals(secSwId)) {
                            break;
                        } else {
                            l++;
                        }
                    }
                    newSecuritySWList.remove(l);
                    dbObject.setSecuritySWList(newSecuritySWList);
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/{id}/remove_object/{objId}")
    Mono<Object> removeObjectObject(@PathVariable Long id, @PathVariable Long objId) {
        return objectService.findById(id)
                .flatMap(dbObject -> {
                    List<Object> newObjectList = dbObject.getObjectList();
                    int l = 0;
                    for (Object o : newObjectList) {
                        if (o.getObj_id().equals(objId)) {
                            break;
                        } else {
                            l++;
                        }
                    }
                    newObjectList.remove(l);
                    dbObject.setObjectList(newObjectList);
                    return objectService.save(dbObject);
                });
    }

    @PutMapping("/{id}/remove_criteria_object/{objId}")
    Mono<Object> removeCriteriaObject(@PathVariable Long id, @PathVariable Long objId) {
        return objectService.findById(id)
                .flatMap(dbObject -> {
                    List<Object> newCriteriaObjectList = dbObject.getAndCriteriaList();
                    int l = 0;
                    for (Object o : newCriteriaObjectList) {
                        if (o.getObj_id().equals(objId)) {
                            break;
                        } else {
                            l++;
                        }
                    }
                    newCriteriaObjectList.remove(l);
                    dbObject.setAndCriteriaList(newCriteriaObjectList);
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
