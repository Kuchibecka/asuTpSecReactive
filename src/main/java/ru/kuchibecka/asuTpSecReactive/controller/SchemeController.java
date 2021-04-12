package ru.kuchibecka.asuTpSecReactive.controller;

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
import java.util.stream.Collectors;

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
                    List<Node> nodeList = new ArrayList<Node>();
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

    @GetMapping("/{id}/relationss")
    Mono<List<Relationship>> getRelationsByIdd(@PathVariable Long id) {
        return schemeService
                .findRelationsById(id)
                .collectList()
                .flatMapIterable(a -> {
                        List<Relationship> relationshipList = new ArrayList<>();
                        //relationshipList.add(a);
                        return relationshipList;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/relationsss")
    Mono<List<Object>> getRelationsByIddd(@PathVariable Long id) {
        return schemeService.findById(id).map(a -> a.getObjectList());
    }

    @GetMapping("/{id}/relations")
    Flux<java.lang.Object> getRelationsById(@PathVariable Long id) {
        return schemeService
                .findRelationsById(id)
                .map(r -> {
                    System.out.println(r);
                    Relationship rel = new Relationship(r.getStartId(), r.getEndId());
                    return Flux.just(rel)
                            .subscribe(v -> System.out.println(v));
                });
    }





                /*
                .subscribe() {data => System.out.println(data);}
                .flatMapIterable(a -> {
                    List<Relationship> relationshipList = new ArrayList<Relationship>();
                    relationshipList.add(new Relationship(a.getId(), a.getSource(), a.getTarget(), "step", false));
                    /*
                    List<Object> objectList = a.getObjectList();
                    for (Object el : objectList) {
                        Node node = new Node(
                                el.getObj_id().toString(),
                                el.getName()
                        );
                        nodeList.add(node);
                    }

                    return relationshipList;
                });
        schemeService.findRelationsById(id)
                */
}
