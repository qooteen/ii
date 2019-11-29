import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws Exception {
        List<String> input = Parser.readFromFile("input.txt");
        Graph graph = Parser.parse(input);
        List<Edge> sorted = sort(graph.getGraph());
        graph.setGraph(sorted);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("graph.json"), graph);
    }
    public static int findMaxx(List<Edge> edges) {
        int maxx = -1;
        for (Edge e: edges) {
            int maxxab = Math.max(e.getA(), e.getB());
            maxx = Math.max(maxxab, maxx);
        }
        return maxx;
    }

    public static List<Edge> sort(List<Edge> edges) {
        Comparator<Edge> comparator = Comparator.comparing(edge -> edge.getB());
        comparator = comparator.thenComparing((edge -> edge.getN()));
        Stream<Edge> edgeStream = edges.stream().sorted(comparator);
        List<Edge> sorted = edgeStream.collect(Collectors.toList());
        return sorted;
    }

    public static void dfs(int v, int[] colors, List<Edge> edges) throws Exception{
        colors[v - 1] = 1;
        for (Edge e: edges) {
            if (e.getA() == v) {
                if (colors[e.getB() - 1] == 0) {
                    dfs(e.getA(), colors, edges);
                }
                if (colors[e.getB() - 1] == 1) {
                    throw new Exception("Error! Cycle find!");
                }
            }
        }
        colors[v - 1] = 2;
    }
}
