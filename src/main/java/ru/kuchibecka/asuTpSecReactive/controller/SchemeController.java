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
import ru.kuchibecka.asuTpSecReactive.service.SchemeService;

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

    @PutMapping("/edit/{id}")
    Mono<Scheme> updateScheme(@PathVariable Long id, @RequestBody Scheme scheme) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    BeanUtils.copyProperties(scheme, sch);
                    return schemeService.save(sch);
                });
    }

    // todo: 1) Протестить
    // todo: 2) Реализовать добавление вируса, СЗИ, объекта в дерево отказов схемы
    @PutMapping("/add_object/{id}")
    Mono<Scheme> addSchemeObject(@PathVariable Long id, @RequestBody Scheme scheme, @RequestBody Object object) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    BeanUtils.copyProperties(scheme, sch);
                    List<Object> newObjectList = sch.getObjectList();
                    newObjectList.add(object);
                    sch.setObjectList(newObjectList);
                    return schemeService.save(sch);
                });
    }

    @PutMapping("/add_virus/{id}")
    Mono<Scheme> addSchemeVirus(@PathVariable Long id, @RequestBody Scheme scheme, @RequestBody Virus virus) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    BeanUtils.copyProperties(scheme, sch);
                    List<Virus> newVirusList = sch.getVirusList();
                    newVirusList.add(virus);
                    sch.setVirusList(newVirusList);
                    return schemeService.save(sch);
                });
    }

    @PutMapping("/add_securitysw/{id}")
    Mono<Scheme> addSchemeSecuritySW(@PathVariable Long id, @RequestBody Scheme scheme, @RequestBody SecuritySW securitySW) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    BeanUtils.copyProperties(scheme, sch);
                    List<SecuritySW> newSecuritySWList = sch.getSecuritySWList();
                    newSecuritySWList.add(securitySW);
                    sch.setSecuritySWList(newSecuritySWList);
                    return schemeService.save(sch);
                });
    }

    @PutMapping("/add_object/{id}")
    Mono<Scheme> addSchemeCriteriaObject(@PathVariable Long id, @RequestBody Scheme scheme, @RequestBody Object object) {
        return schemeService.findById(id)
                .flatMap(sch -> {
                    BeanUtils.copyProperties(scheme, sch);
                    List<Object> newCriteriaObjectList = sch.getCriteriaList();
                    newCriteriaObjectList.add(object);
                    sch.setObjectList(newCriteriaObjectList);
                    return schemeService.save(sch);
                });
    }
    // todo: put-mapping: Удалить всё вышеперечисленное
}
