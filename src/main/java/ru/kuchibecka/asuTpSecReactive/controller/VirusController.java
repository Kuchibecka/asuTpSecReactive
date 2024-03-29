package ru.kuchibecka.asuTpSecReactive.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.kuchibecka.asuTpSecReactive.entity.Exploit;
import ru.kuchibecka.asuTpSecReactive.entity.Object;
import ru.kuchibecka.asuTpSecReactive.entity.SecuritySW;
import ru.kuchibecka.asuTpSecReactive.entity.Virus;
import ru.kuchibecka.asuTpSecReactive.service.ExploitService;
import ru.kuchibecka.asuTpSecReactive.service.VirusService;

import java.util.List;

@RestController
@RequestMapping("api/virus")
@CrossOrigin(origins = "http://localhost:3000")
public class VirusController {
    @Autowired
    private VirusService virusService;

    @Autowired
    private ExploitService exploitService;

    @GetMapping(path = "")
    Flux<Virus> getViruses() {
        return virusService.findAll();
    }

    @GetMapping("/{id}")
    Mono<Virus> getById(@PathVariable Long id) {
        return virusService.findById(id);
    }

    @GetMapping("/by-name")
    Flux<Virus> byName(@RequestParam("name") String name) {
        return virusService.getVirusByName(name);
    }

    @PostMapping("")
    Mono<Virus> createVirus(@RequestBody Virus Virus) {
        return virusService.save(Virus);
    }

    @PutMapping("/{id}/edit/")
    Mono<Virus> updateVirus(@PathVariable Long id, @RequestBody Virus Virus) {
        return virusService.findById(id)
                .flatMap(dbVirus -> {
                    BeanUtils.copyProperties(Virus, dbVirus);
                    return virusService.save(dbVirus);
                });
    }

    @DeleteMapping("/{id}/delete/")
    Mono<Void> deleteVirus(@PathVariable Long id) {
        return virusService.findById(id)
                .flatMap(Virus ->
                        virusService.delete(Virus)
                );
    }

    @PutMapping("/{id}/add_exploit/")
    Mono<Virus> addVirusExploit(@PathVariable Long id, @RequestBody Exploit exploit) {
        return virusService.findById(id)
                .flatMap(dbVirus -> {
                    List<Exploit> newExploitList = dbVirus.getVirusExploit();
                    newExploitList.add(exploit);
                    dbVirus.setVirusExploit(newExploitList);
                    return virusService.save(dbVirus);
                });
    }

    @PutMapping("/{id}/remove_exploit/{expId}")
    Mono<Virus> removeVirusExploit(@PathVariable Long id, @PathVariable Long expId) {
        return virusService.findById(id)
                .flatMap(dbVirus -> {
                    List<Exploit> newExploitList = dbVirus.getVirusExploit();
                    int l = 0;
                    for (Exploit o : newExploitList) {
                        if (o.getSE_id().equals(expId)) {
                            break;
                        } else {
                            l++;
                        }
                    }
                    newExploitList.remove(l);
                    dbVirus.setVirusExploit(newExploitList);
                    return virusService.save(dbVirus);
                });
    }

    /*@PostMapping("/new_instance/{id}")
    Mono<Virus> createVirusInstance(@PathVariable Long id) {
        return virusService.findById(id)
                .flatMap(obj -> {
                    Virus instance = new Virus();
                    BeanUtils.copyProperties(obj, instance, "virus_id");
                    instance.setIsInstance(true);
                    return virusService.save(instance);
                });

    }*/
}
