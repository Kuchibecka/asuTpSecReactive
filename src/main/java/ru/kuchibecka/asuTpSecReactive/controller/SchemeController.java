package ru.kuchibecka.asuTpSecReactive.controller;

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
                    final String TYPE = "default";
                    final boolean ANIMATED = false;
                    final Relationship.Style STYLE = new Relationship.Style("default");
                    for (Object o : objectList) {
                        connectedTo = o.getObjectList();
                        for (Object obj : connectedTo) {
                            String relId = "e" + o.getObj_id() + "-" + obj.getObj_id();
                            Relationship relationship = new Relationship(
                                    relId,
                                    o.getObj_id().toString(), obj.getObj_id().toString(),
                                    TYPE,
                                    ANIMATED,
                                    "",
                                    STYLE
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
                    List<Object> objectList = a.getObjectList();
                    List<Virus> virusList;
                    List<Node> nodeList = new ArrayList<>();
                    for (Object o : objectList) {
                        virusList = o.getVirusList();
                        // System.out.println(virusList);
                        for (Virus vi : virusList) {
                            Node virus = new Node(
                                    "virus" + vi.getVirus_id().toString(),
                                    vi.getName()
                            );
                            nodeList.add(virus);
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
                    final String TYPE = "default";
                    final boolean ANIMATED = true;
                    final String LABEL = "заражает";
                    final Relationship.Style STYLE = new Relationship.Style("red");
                    for (Object o : objectList) {
                        virusList = o.getVirusList();
                        for (Virus v : virusList) {
                            String virusId = "virus" + v.getVirus_id().toString();
                            String relId = "e" + virusId + "-" + o.getObj_id();
                            Relationship relationship = new Relationship(
                                    relId,
                                    virusId, o.getObj_id().toString(),
                                    TYPE,
                                    ANIMATED,
                                    LABEL,
                                    STYLE
                            );
                            infectionList.add(relationship);
                        }
                    }
                    return infectionList;
                })
                .flatMapMany(Flux::fromIterable);
    }

    @GetMapping("/{id}/fault_tree_nodes")
    Flux<Node> getTreeNodesById(@PathVariable Long id) {
        return schemeService
                .findById(id)
                .flatMapIterable(a -> {
                    List<Object> criteriaList = a.getCriteriaList();
                    List<Node> treeNodeList = new ArrayList<>();
                    Node root = new Node(
                            id + "treeRoot",
                            "Отказ системы " + a.getName(),
                            200,
                            0
                    );
                    treeNodeList.add(root);
                    int level3 = 0;
                    for (Object el : criteriaList) {
                        Node node = new Node(
                                el.getObj_id().toString(),
                                el.getName(),
                                level3,
                                200
                        );
                        level3+=200;
                        treeNodeList.add(node);
                        int level2 = 300;
                        for (Object and : el.getAndCriteriaList()) {
                            Node andNode = new Node(
                                    el.getObj_id().toString() + "_" + and.getObj_id(),
                                    "И",
                                    level2,
                                    100
                            );
                            treeNodeList.add(andNode);
                            level2+=300;
                        }
                    }
                    return treeNodeList;
                });
    }

    @GetMapping("/{id}/fault_tree_relations")
    Flux<Relationship> getTreeRelationsById(@PathVariable Long id) {
        return schemeService
                .findById(id)
                .map(a -> {
                    final Relationship.Style STYLE = new Relationship.Style("black");
                    final String TYPE = "step";
                    final boolean ANIMATED = false;
                    List<Object> criteriaList = a.getCriteriaList();
                    List<Relationship> treeRelationshipList = new ArrayList<>();
                    List<Object> andConnection;
                    List<Long> markedAnd = new ArrayList<>();
                    for (Object o : criteriaList) {
                        andConnection = o.getAndCriteriaList();
                        for (Object andObj : andConnection) {
                            String relId = "e" + o.getObj_id() + "-" + andObj.getObj_id();
                            Relationship relationship1 = new Relationship(
                                    relId + "_andSide1",
                                    o.getObj_id().toString() + "_" + andObj.getObj_id().toString(), andObj.getObj_id().toString(),
                                    TYPE,
                                    ANIMATED,
                                    "",
                                    STYLE
                            );
                            treeRelationshipList.add(relationship1);
                            Relationship relationship2 = new Relationship(
                                    relId + "_andSide2",
                                    o.getObj_id().toString() + "_" + andObj.getObj_id().toString(), o.getObj_id().toString(),
                                    TYPE,
                                    ANIMATED,
                                    "",
                                    STYLE
                            );
                            treeRelationshipList.add(relationship2);
                            Relationship andToRoot = new Relationship(
                                    relId + "_andToRoot",
                                    id + "treeRoot", o.getObj_id().toString() + "_" + andObj.getObj_id().toString(),
                                    TYPE,
                                    ANIMATED,
                                    "ИЛИ",
                                    STYLE
                            );
                            treeRelationshipList.add(andToRoot);
                            markedAnd.add(andObj.getObj_id());
                            markedAnd.add(o.getObj_id());
                        }
                        if (andConnection.isEmpty() && !markedAnd.contains(o.getObj_id())) {
                            Relationship orToRoot = new Relationship(
                                    id + "_orToRoot",
                                    id + "treeRoot", o.getObj_id().toString(),
                                    TYPE,
                                    ANIMATED,
                                    "ИЛИ",
                                    STYLE
                            );
                            treeRelationshipList.add(orToRoot);
                        }
                    }
                    return treeRelationshipList;
                })
                .flatMapMany(Flux::fromIterable);
    }
}
