package ru.kuchibecka.asuTpSecReactive.entity.modeling;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Vertex {
    private final Long id;
    private List<Long> securityExploits;
    private List<Long> virusExploits;
    private boolean isInfected;
    private boolean visited;

    public Vertex(Long id, List<Long> securitySWs, List<Long> viruses) {
        this.id = id;
        this.securityExploits = securitySWs; // заранее собрать снаружи
        this.virusExploits = viruses;        // заранее собрать снаружи
        this.visited = false;
        if (!virusExploits.isEmpty()) {
            this.isInfected = !securityExploits.containsAll(virusExploits);
        }
    }
}
