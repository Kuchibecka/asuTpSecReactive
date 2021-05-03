package ru.kuchibecka.asuTpSecReactive.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kuchibecka.asuTpSecReactive.entity.Exploit;
import ru.kuchibecka.asuTpSecReactive.entity.SecuritySW;
import ru.kuchibecka.asuTpSecReactive.service.ExploitService;
import ru.kuchibecka.asuTpSecReactive.service.SecuritySWService;

import java.util.List;

@RestController
@RequestMapping("api/securitysw")
@CrossOrigin(origins = "http://localhost:3000")
public class SecuritySWController {
    @Autowired
    private SecuritySWService securitySWService;

    @Autowired
    private ExploitService exploitService;

    @GetMapping(path = "")
    Flux<SecuritySW> getSecuritySWs() {
        return securitySWService.findAll();
    }

    @GetMapping("/{id}")
    Mono<SecuritySW> getById(@PathVariable Long id) {
        return securitySWService.findById(id);
    }

    @GetMapping("/by-name")
    Flux<SecuritySW> byName(@RequestParam("name") String name) {
        return securitySWService.getSecuritySWByName(name);
    }

    @PostMapping("")
    Mono<SecuritySW> createSecuritySW(@RequestBody SecuritySW securitySW) {
        return securitySWService.save(securitySW);
    }

    @PutMapping("/{id}/edit/")
    Mono<SecuritySW> updateSecuritySW(@PathVariable Long id, @RequestBody SecuritySW securitySW) {
        return securitySWService.findById(id)
                .flatMap(dbSecuritySW -> {
                    BeanUtils.copyProperties(securitySW, dbSecuritySW);
                    return securitySWService.save(dbSecuritySW);
                });
    }

    @DeleteMapping("/{id}/delete/")
    Mono<Void> deleteSecuritySW(@PathVariable Long id) {
        return securitySWService.findById(id)
                .flatMap(SecuritySW ->
                        securitySWService.delete(SecuritySW)
                );
    }

    @PutMapping("/{id}/add_exploit/{expId}")
    Mono<SecuritySW> addSecuritySWExploit(@PathVariable Long id, @PathVariable Long expId) {
        return securitySWService.findById(id)
                .flatMap(dbSecuritySW -> {
                    List<Exploit> newExploitList = dbSecuritySW.getSecurityExploit();
                    exploitService.findById(expId)
                            .subscribe(v -> {
                                if (newExploitList.contains(v))
                                    return;
                                newExploitList.add(v);
                                dbSecuritySW.setSecurityExploit(newExploitList);
                            });
                    return securitySWService.save(dbSecuritySW);
                });
    }

    @PutMapping("/{id}/remove_exploit/{expId}")
    Mono<SecuritySW> removeSecuritySWExploit(@PathVariable Long id, @PathVariable Long expId) {
        return securitySWService.findById(id)
                .flatMap(dbSecuritySW -> {
                    List<Exploit> newExploitList = dbSecuritySW.getSecurityExploit();
                    exploitService.findById(expId)
                            .subscribe(v -> {
                                if (!newExploitList.contains(v))
                                    return;
                                newExploitList.remove(v);
                                dbSecuritySW.setSecurityExploit(newExploitList);
                            });
                    return securitySWService.save(dbSecuritySW);
                });
    }
}
