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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
                    ArrayList<Vertex> vertexList = new ArrayList<>();
                    int size = objectList.size();
                    int[][] adjMatrix = new int[size][size];
                    Map<Integer, Integer> index = new HashMap<>();
                    int i = 0;
                    for (Object o : objectList) {
                        index.put(o.getObj_id().intValue(), i);
                        i++;
                    }
                    System.out.println("Соответствие: " + index);
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
                            System.out.println("For virus " + v.getVirus_id() + " viruses: " + viruses.toString());
                        }
                        vertexList.add(new Vertex(index.get(o.getObj_id().intValue()), securitySWs, viruses));
                        for (Object connected : o.getObjectList()) {
                            int source = index.get(o.getObj_id().intValue());
                            int target = index.get(connected.getObj_id().intValue());
                            adjMatrix[source][target] = 1;
                            adjMatrix[target][source] = 1;
                        }
                    }
                    //todo: убрать, это для отладки
                    System.out.println("vertexList: ");
                    for (Vertex vertex : vertexList) {
                        System.out.println(vertex.toString());
                    }
                    ArrayList<ArrayList<Integer>> treeAndRelations = new ArrayList<>();
                    ArrayList<Integer> treeOrRelations = new ArrayList<>();
                    for (Object o : criteriaList) {
                        treeOrRelations.add(index.get(o.getObj_id().intValue()));
                        if (!o.getAndCriteriaList().isEmpty()) {
                            ArrayList<Integer> andRelation = new ArrayList<>();
                            andRelation.add(index.get(o.getObj_id().intValue()));
                            for (Object and : o.getAndCriteriaList()) {
                                andRelation.add(index.get(and.getObj_id().intValue()));
                            }
                            treeAndRelations.add(andRelation);
                        }
                    }
                    for (ArrayList<Integer> andRel : treeAndRelations) {
                        for (Integer j : andRel) {
                            treeOrRelations.remove(j);
                        }
                    }
                    System.out.println("Summary and relations: " + treeAndRelations);
                    System.out.println("Summary or relations: " + treeOrRelations);
                    Graph graph = new Graph(vertexList, adjMatrix, treeAndRelations, treeOrRelations);
                    graph.bfc();
                    return schemeService.findById(id);
                });
        // return schemeService.findById(id);
    }
}
