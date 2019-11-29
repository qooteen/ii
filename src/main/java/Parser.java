import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class Parser {

    public static Graph parse(List<String> strings) throws Exception{

        HashMap<Integer, List<Integer>> listNumbers = new HashMap<>();
        Graph graph = new Graph();
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < strings.size(); i++) {

            String s = strings.get(i).trim();
            String regex = "(\\s*[(]\\s*\\d?\\d?\\d\\s*[,]\\s*\\d?\\d?\\d\\s*[,]\\s*\\d?\\d?\\d\\s*[)]\\s*[,]\\s*)*";

            if ((i != strings.size() - 1 && Pattern.matches(regex, s))
                    || (i == strings.size() - 1 && Pattern.matches(regex, s + ","))) {

                String[] triple = s.split("\\s*[)]\\s*,\\s*[(]?");

                for (int j = 0; j < triple.length; j++) {
                    triple[j] = triple[j].replaceAll("[()]", "");
                    String[] oneEdge = triple[j].split(",");
                    Edge edge = new Edge();
                    edge.setA(Integer.parseInt(oneEdge[0].replaceAll(" ", "")));
                    edge.setB(Integer.parseInt(oneEdge[1].replaceAll(" ", "")));
                    edge.setN(Integer.parseInt(oneEdge[2].replaceAll(" ", "")));
                    for (Edge e: edges) {
                        if (e.getA() == edge.getA() && e.getB() == edge.getB()) {
                            throw new Exception("Input Error! Number of line: " + (1 + i));
                        }
                    }
                    edges.add(edge);
                    if (listNumbers.get(edge.getB()) == null) {
                        List<Integer> e = new ArrayList<>();
                        e.add(edge.getN());
                        listNumbers.put(edge.getB(), e);
                    } else {
                        listNumbers.get(edge.getB()).add(edge.getN());
                    }
                }
            } else {
                throw new Exception("Input Syntax Error! Number of line: " + (1 + i));
            }
        }
        for (Map.Entry<Integer, List<Integer>> pair: listNumbers.entrySet()) {
            List<Integer> numbers = pair.getValue();
            numbers.sort(Comparator.naturalOrder());
            if (numbers.get(0) != 1) {
                throw new Exception("Incorrect numbering od edges!");
            }
            if (numbers.get(numbers.size() - 1) - numbers.get(0) + 1 != numbers.size()) {
                throw new Exception("Incorrect numbering od edges!");
            }
        }
        graph.setGraph(edges);
        return graph;
    }

    public static List<String> readFromFile(String fileName) {
        List<String> result = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new FileReader(fileName));
            while (scanner.hasNext()) {
                result.add(scanner.nextLine());
            }
            scanner.close();
        }
        catch (IOException e) {
            System.out.println("Error reading from file");
        }
        return result;
    }
}
