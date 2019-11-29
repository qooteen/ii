import org.codehaus.jackson.annotate.JsonAutoDetect;
import java.io.Serializable;
import java.util.List;

@JsonAutoDetect
public class Graph implements Serializable{
    private List<Edge> graph;

    public List<Edge> getGraph() {
        return graph;
    }

    public void setGraph(List<Edge> graph) {
        this.graph = graph;
    }
}
