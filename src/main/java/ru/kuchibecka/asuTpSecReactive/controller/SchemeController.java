package ru.kuchibecka.asuTpSecReactive.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kuchibecka.asuTpSecReactive.entity.*;
import ru.kuchibecka.asuTpSecReactive.entity.Object;
import ru.kuchibecka.asuTpSecReactive.entity.modeling.Graph;
import ru.kuchibecka.asuTpSecReactive.entity.modeling.Vertex;
import ru.kuchibecka.asuTpSecReactive.service.SchemeService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/scheme")
@CrossOrigin(origins = "http://localhost:3000")
public class SchemeController {
    @Autowired
    private SchemeService schemeService;

    @GetMapping(path = "")
    Flux<Scheme> getSchemes() {
        return schemeService
                .findAll();
    }

    @GetMapping("/{id}")
    Mono<Scheme> getById(@PathVariable Long id) {
        return schemeService
                .findById(id);
    }

    @GetMapping("/by-name")
    Flux<Scheme> byName(@RequestParam("name") String name) {
        return schemeService
                .getSchemeByName(name);
    }

    @PostMapping("")
    Mono<Scheme> createScheme(@RequestBody Scheme scheme) {
        return schemeService.save(scheme);
    }

    @PutMapping("/{id}/edit/")
    Mono<Scheme> updateScheme(@PathVariable Long id, @RequestBody Scheme scheme) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    BeanUtils.copyProperties(scheme, sch);
                    return schemeService.save(sch);
                });
    }

    @DeleteMapping("/{id}/delete/")
    Mono<Void> deleteScheme(@PathVariable Long id) {
        return schemeService.findById(id)
                .flatMap(scheme ->
                        schemeService.delete(scheme)
                );
    }

    @PutMapping("/{id}/add_object/")
    Mono<Scheme> addSchemeObject(@PathVariable Long id, @RequestBody Object object) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    List<Object> newObjectList = sch.getObjectList();
                    newObjectList.add(object);
                    sch.setObjectList(newObjectList);
                    return schemeService.save(sch);
                });
    }

    @PutMapping("/{id}/add_virus/")
    Mono<Scheme> addSchemeVirus(@PathVariable Long id, @RequestBody Virus virus) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    List<Virus> newVirusList = sch.getVirusList();
                    newVirusList.add(virus);
                    sch.setVirusList(newVirusList);
                    return schemeService.save(sch);
                });
    }

    @PutMapping("/{id}/add_securitysw/")
    Mono<Scheme> addSchemeSecuritySW(@PathVariable Long id, @RequestBody SecuritySW securitySW) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    List<SecuritySW> newSecuritySWList = sch.getSecuritySWList();
                    newSecuritySWList.add(securitySW);
                    sch.setSecuritySWList(newSecuritySWList);
                    return schemeService.save(sch);
                });
    }

    @PutMapping("/{id}/add_criteria_object/")
    Mono<Scheme> addSchemeCriteriaObject(@PathVariable Long id, @RequestBody Object object) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    List<Object> newCriteriaObjectList = sch.getCriteriaList();
                    newCriteriaObjectList.add(object);
                    sch.setCriteriaList(newCriteriaObjectList);
                    return schemeService.save(sch);
                });
    }

    @PutMapping("/{id}/remove_object/{obj}")
    Mono<Scheme> removeSchemeObject(@PathVariable Long id, @PathVariable Long obj) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    List<Object> newObjectList = sch.getObjectList();
                    int l = 0;
                    for (Object o : newObjectList) {
                        if (o.getObj_id().equals(obj)) {
                            break;
                        } else {
                            l++;
                        }
                    }
                    newObjectList.remove(l);
                    sch.setObjectList(newObjectList);
                    return schemeService.save(sch);
                });
    }

    @PutMapping("/{id}/remove_virus/{virusId}")
    Mono<Scheme> removeSchemeVirus(@PathVariable Long id, @PathVariable Long virusId) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    List<Virus> newVirusList = sch.getVirusList();
                    int l = 0;
                    for (Virus o : newVirusList) {
                        if (o.getVirus_id().equals(virusId)) {
                            break;
                        } else {
                            l++;
                        }
                    }
                    newVirusList.remove(l);
                    sch.setVirusList(newVirusList);
                    return schemeService.save(sch);
                });
    }

    @PutMapping("/{id}/remove_securitysw/{secSwId}")
    Mono<Scheme> removeSchemeSecuritySW(@PathVariable Long id, @PathVariable Long secSwId) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    List<SecuritySW> newSecuritySWList = sch.getSecuritySWList();
                    int l = 0;
                    for (SecuritySW o : newSecuritySWList) {
                        if (o.getSecSW_id().equals(secSwId)) {
                            break;
                        } else {
                            l++;
                        }
                    }
                    newSecuritySWList.remove(l);
                    sch.setSecuritySWList(newSecuritySWList);
                    return schemeService.save(sch);
                });
    }

    @PutMapping("/{id}/remove_criteria_object/{crObjId}")
    Mono<Scheme> removeSchemeCriteriaObject(@PathVariable Long id, @PathVariable Long crObjId) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    List<Object> newCriteriaObjectList = sch.getCriteriaList();
                    int l = 0;
                    for (Object o : newCriteriaObjectList) {
                        if (o.getObj_id().equals(crObjId)) {
                            break;
                        } else {
                            l++;
                        }
                    }
                    newCriteriaObjectList.remove(l);
                    sch.setCriteriaList(newCriteriaObjectList);
                    return schemeService.save(sch);
                });
    }

    @GetMapping("/{id}/model")
    Mono<Scheme> modeling(@PathVariable Long id) {
        return schemeService.findById(id)
                .flatMap(scheme -> {
                    Boolean result;
                    List<Object> objectList = scheme.getObjectList();
                    List<Object> criteriaList = scheme.getCriteriaList();
                    List<Object> totalAndList = new ArrayList<>();
                    List<Object> totalOrList = criteriaList;
                    /*
                    System.out.println("Все объекты: ");
                    for (Object o : objectList) {
                        System.out.println(o.getName());
                    }
                    System.out.println("Объекты и связи: ");
                    for (Object o : objectList) {
                        for (Object o1 : o.getObjectList()) {
                            System.out.println(o.getName() + " connected to " + o1.getName());
                        }
                    }
                    System.out.println("Дерево откаазов: ");
                    System.out.println("Все связи И: ");
                    for (Object o : objectList) {
                        List<Object> andList = o.getAndCriteriaList();
                        totalAndList.addAll(andList);
                        if (!andList.isEmpty()) {
                            totalAndList.add(o);
                        }
                        for (Object and : andList) {
                            System.out.println(o.getName() + " AND " + and.getName());
                        }
                    }
                    totalOrList.removeAll(totalAndList);
                    System.out.println("Все или: ");
                    for (Object o : totalOrList) {
                        System.out.println(o.getName());
                    }*/
                    Graph graph = new Graph();
                    for (Object o : objectList) {
                        List<SecuritySW> securitySWList = o.getSecuritySWList();
                        List<Long> securitySWs = new ArrayList<>();
                        for (SecuritySW s : securitySWList) {
                            for (Exploit e : s.getSecurityExploit()) {
                                securitySWs.add(e.getSE_id());
                            }
                        }
                        List<Virus> virusList = o.getVirusList();
                        List<Long> viruses = new ArrayList<>();
                        for (Virus v : virusList) {
                            for (Exploit e : v.getVirusExploit()) {
                                viruses.add(e.getSE_id());
                            }
                        }
                        graph.addVertex(new Vertex(o.getObj_id(), securitySWs, viruses));
                        for (Object connected : o.getObjectList()) {
                            graph.addEdge(o.getObj_id().intValue(), connected.getObj_id().intValue());
                        }
                    }
                    graph.bfc();
                    return schemeService.findById(id);
                });
        // return schemeService.findById(id);
    }
}
