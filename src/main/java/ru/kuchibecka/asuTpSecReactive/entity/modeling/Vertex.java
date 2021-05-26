package ru.kuchibecka.asuTpSecReactive.entity.modeling;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Vertex {
    private final int id;
    private List<Long> securityExploits;
    private List<Long> virusExploits;
    private boolean isInfected;
    private boolean visited;

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", securityExploits=" + securityExploits +
                ", virusExploits=" + virusExploits +
                ", isInfected=" + isInfected +
                ", visited=" + visited +
                '}';
    }

    public Vertex(int id, List<Long> securitySWs, List<Long> viruses) {
        this.id = id;
        this.securityExploits = securitySWs; // заранее собрать снаружи
        this.virusExploits = viruses;        // заранее собрать снаружи
        this.visited = false;
        if (!virusExploits.isEmpty()) {
            this.isInfected = !securityExploits.containsAll(virusExploits);
        }
    }
}
