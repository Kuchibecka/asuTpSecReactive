package ru.kuchibecka.asuTpSecReactive.controller;

import org.neo4j.driver.internal.value.ListValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kuchibecka.asuTpSecReactive.entity.graph.Node;
import ru.kuchibecka.asuTpSecReactive.entity.Object;
import ru.kuchibecka.asuTpSecReactive.entity.Scheme;
import ru.kuchibecka.asuTpSecReactive.entity.graph.Relationship;
import ru.kuchibecka.asuTpSecReactive.service.SchemeService;

import java.util.*;

@RestController
@RequestMapping("/api/scheme")
@CrossOrigin("*")
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
    Mono<List<Relationship>> getRelationsById(@PathVariable Long id) {
        return schemeService.findById(id).map(a -> {
            List<Object> objectList = a.getObjectList();
            List<Relationship> relationshipList = new ArrayList<>();
            List<Object> connectedTo;
            for(Object o : objectList) {
                connectedTo = o.getObjectList();
                for (Object obj : connectedTo) {
                    Relationship relationship = new Relationship(
                            o.getObj_id(), obj.getObj_id()
                    );
                    relationshipList.add(relationship);
                }

            }
            return relationshipList;
        });
    }
}
