package ru.kuchibecka.asuTpSecReactive.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kuchibecka.asuTpSecReactive.entity.Object;
import ru.kuchibecka.asuTpSecReactive.entity.Scheme;
import ru.kuchibecka.asuTpSecReactive.entity.SecuritySW;
import ru.kuchibecka.asuTpSecReactive.entity.Virus;
import ru.kuchibecka.asuTpSecReactive.service.ObjectService;
import ru.kuchibecka.asuTpSecReactive.service.SchemeService;
import ru.kuchibecka.asuTpSecReactive.service.SecuritySWService;
import ru.kuchibecka.asuTpSecReactive.service.VirusService;

import java.util.List;


@RestController
@RequestMapping("/api/scheme")
@CrossOrigin(origins = "http://localhost:3000")
public class SchemeController {
    @Autowired
    private SchemeService schemeService;

    @Autowired
    private ObjectService objectService;

    @Autowired
    private VirusService virusService;

    @Autowired
    private SecuritySWService securitySWService;

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

    @PutMapping("/{id}/add_object/{objId}")
    Mono<Scheme> addSchemeObject(@PathVariable Long id, @PathVariable Long objId) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    List<Object> newObjectList = sch.getObjectList();
                    objectService.findById(objId)
                            .subscribe(v -> {
                                if (newObjectList.contains(v))
                                    return;
                                newObjectList.add(v);
                                sch.setObjectList(newObjectList);
                            });
                    return schemeService.save(sch);
                });
    }

    @PutMapping("/{id}/add_virus/{virusId}")
    Mono<Scheme> addSchemeVirus(@PathVariable Long id, @PathVariable Long virusId) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    List<Virus> newVirusList = sch.getVirusList();
                    virusService.findById(virusId)
                            .subscribe(v -> {
                                if (newVirusList.contains(v))
                                    return;
                                newVirusList.add(v);
                                sch.setVirusList(newVirusList);
                            });
                    return schemeService.save(sch);
                });
    }

    @PutMapping("/{id}/add_securitysw/{secSwId}")
    Mono<Scheme> addSchemeSecuritySW(@PathVariable Long id, @PathVariable Long secSwId) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    List<SecuritySW> newSecuritySWList = sch.getSecuritySWList();
                    securitySWService.findById(secSwId)
                            .subscribe(v -> {
                                if (newSecuritySWList.contains(v))
                                    return;
                                newSecuritySWList.add(v);
                                sch.setSecuritySWList(newSecuritySWList);
                            });
                    return schemeService.save(sch);
                });
    }

    @PutMapping("/{id}/add_criteria_object/{crObjId}")
    Mono<Scheme> addSchemeCriteriaObject(@PathVariable Long id, @PathVariable Long crObjId) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    List<Object> newCriteriaObjectList = sch.getCriteriaList();
                    objectService.findById(crObjId)
                            .subscribe(v -> {
                                if (newCriteriaObjectList.contains(v))
                                    return;
                                newCriteriaObjectList.add(v);
                                sch.setCriteriaList(newCriteriaObjectList);
                            });
                    return schemeService.save(sch);
                });
    }

    @PutMapping("/{id}/remove_object/{objId}")
    Mono<Scheme> removeSchemeObject(@PathVariable Long id, @PathVariable Long objId) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    List<Object> newObjectList = sch.getObjectList();
                    objectService.findById(objId)
                            .subscribe(v -> {
                                if (!newObjectList.contains(v))
                                    return;
                                newObjectList.remove(v);
                                sch.setObjectList(newObjectList);
                            });
                    return schemeService.save(sch);
                });
    }

    @PutMapping("/{id}/remove_virus/{virusId}")
    Mono<Scheme> removeSchemeVirus(@PathVariable Long id, @PathVariable Long virusId) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    List<Virus> newVirusList = sch.getVirusList();
                    virusService.findById(virusId)
                            .subscribe(v -> {
                                if (!newVirusList.contains(v))
                                    return;
                                newVirusList.remove(v);
                                sch.setVirusList(newVirusList);
                            });
                    return schemeService.save(sch);
                });
    }

    @PutMapping("/{id}/remove_securitysw/")
    Mono<Scheme> removeSchemeSecuritySW(@PathVariable Long id, @PathVariable Long secSwId) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    List<SecuritySW> newSecuritySWList = sch.getSecuritySWList();
                    securitySWService.findById(secSwId)
                            .subscribe(v -> {
                                if (!newSecuritySWList.contains(v))
                                    return;
                                newSecuritySWList.remove(v);
                                sch.setSecuritySWList(newSecuritySWList);
                            });
                    return schemeService.save(sch);
                });
    }

    @PutMapping("/{id}/remove_criteria_object/{crObjId}")
    Mono<Scheme> removeSchemeCriteriaObject(@PathVariable Long id, @PathVariable Long crObjId) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    List<Object> newCriteriaObjectList = sch.getCriteriaList();
                    objectService.findById(crObjId)
                            .subscribe(v -> {
                                if (!newCriteriaObjectList.contains(v))
                                    return;
                                newCriteriaObjectList.remove(v);
                                sch.setCriteriaList(newCriteriaObjectList);
                            });
                    return schemeService.save(sch);
                });
    }
}
