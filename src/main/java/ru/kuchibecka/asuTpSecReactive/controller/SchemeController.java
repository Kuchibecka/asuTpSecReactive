package ru.kuchibecka.asuTpSecReactive.controller;

import org.neo4j.driver.internal.value.ListValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kuchibecka.asuTpSecReactive.entity.Virus;
import ru.kuchibecka.asuTpSecReactive.entity.graph.Node;
import ru.kuchibecka.asuTpSecReactive.entity.Object;
import ru.kuchibecka.asuTpSecReactive.entity.Scheme;
import ru.kuchibecka.asuTpSecReactive.entity.graph.Relationship;
import ru.kuchibecka.asuTpSecReactive.service.SchemeService;

import java.util.*;

@RestController
@RequestMapping("/api/scheme")
@CrossOrigin(origins = "http://localhost:3000")
public class SchemeController {
    @Autowired
    private SchemeService schemeService;

    @GetMapping(path = "")
    Flux<Scheme> getObjects() {
        return schemeService
                .findAll();
    }

    //@GetMapping(path = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @GetMapping("/{id}")
    Mono<Scheme> getById(@PathVariable Long id) {
        return schemeService
                .findById(id);
    }

    @GetMapping("/by-name")
    Flux<Scheme> byName(@RequestParam("name") String name) {
        System.out.println(name);
        return schemeService
                .getObjectByName(name);
    }

    @GetMapping("/{id}/nodes")
    Flux<Node> getNodesById(@PathVariable Long id) {
        return schemeService
                .findById(id)
                .flatMapIterable(a -> {
                    List<Object> objectList = a.getObjectList();
                    List<Node> nodeList = new ArrayList<>();
                    for (Object el : objectList) {
                        Node node = new Node(
                                el.getObj_id().toString(),
                                el.getName()
                        );
                        nodeList.add(node);
                    }
                    return nodeList;
                });
    }

    @GetMapping("/{id}/relations")
    Flux<Relationship> getRelationsById(@PathVariable Long id) {
        return schemeService
                .findById(id)
                .map(a -> {
                    List<Object> objectList = a.getObjectList();
                    List<Relationship> relationshipList = new ArrayList<>();
                    List<Object> connectedTo;
                    for (Object o : objectList) {
                        connectedTo = o.getObjectList();
                        for (Object obj : connectedTo) {
                            String relId = "e" + o.getObj_id() + "-" + obj.getObj_id();
                            String type = "step";
                            boolean animated = false;
                            Relationship relationship = new Relationship(
                                    relId,
                                    o.getObj_id().toString(), obj.getObj_id().toString(),
                                    type,
                                    animated
                            );
                            relationshipList.add(relationship);
                        }
                    }
                    return relationshipList;
                })
                .flatMapMany(Flux::fromIterable);
    }

    @GetMapping("/{id}/viruses")
    Flux<Node> getVirusesById(@PathVariable Long id) {
        return schemeService
                .findById(id)
                .flatMapIterable(a -> {
                    System.out.println(a.getObjectList());
                    List<Object> objectList = a.getObjectList();
                    List<Virus> virusList;
                    List<Node> nodeList = new ArrayList<>();
                    for (Object o : objectList) {
                        virusList = o.getVirusList();
                        System.out.println(virusList);
                        for (Virus vi : virusList) {
                            Node virus = new Node(
                                    "virus" + vi.getVirus_id().toString(),
                                    vi.getName()
                            );
                            nodeList.add(virus);
                            System.out.println(nodeList);
                        }
                    }
                    return nodeList;
                });
    }


    @GetMapping("/{id}/infections")
    Flux<Relationship> getInfectionsById(@PathVariable Long id) {
        return schemeService
                .findById(id)
                .map(a -> {
                    List<Object> objectList = a.getObjectList();
                    List<Relationship> infectionList = new ArrayList<>();
                    List<Virus> virusList;
                    for (Object o : objectList) {
                        virusList = o.getVirusList();
                        for (Virus v : virusList) {
                            String virusId = "virus" + v.getVirus_id().toString();
                            String relId = "e" + virusId + "-" + o.getObj_id();
                            String type = "default";
                            boolean animated = true;
                            Relationship relationship = new Relationship(
                                    relId,
                                    virusId, o.getObj_id().toString(),
                                    type,
                                    animated
                            );
                            infectionList.add(relationship);
                        }
                    }
                    return infectionList;
                })
                .flatMapMany(Flux::fromIterable);
    }


}
