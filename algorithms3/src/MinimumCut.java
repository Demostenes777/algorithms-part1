import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Alaitz Mendiola on 21/10/2016.
 */
public class MinimumCut {

    private static final String FILE_PATH = "C:\\Users\\bgpmeala\\IdeaProjects\\algorithms3\\src\\file";
    private Map<Integer, List<Integer>> graph = new HashMap<>();

    public MinimumCut() {
    }

    public void readFile() {
        List<String> temp = new ArrayList<>();

        try(Stream<String> data = Files.lines(Paths.get(FILE_PATH))) {
            data.forEach(line -> temp.add(line));
            for(String row : temp) {
                String[] strA = row.split("\\t");
                List<Integer> intL = new ArrayList<>();
                for (int i = 1; i < strA.length; i++) {
                    intL.add(Integer.parseInt(strA[i]));
                }
                graph.put(Integer.parseInt(strA[0]), intL);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int applyAlgorithm() {
        while(graph.keySet().size() > 2) {
            // the node that will absorb the other node
            int u = pickRandomVertex();
            // the node that gets contracted
            int v = graph.get(u).get(0);
            // we remove the self loop(S) towards the absorbing node.
            while(graph.get(v).contains(u)) {
                graph.get(v).remove(graph.get(v).indexOf(u));
            }
            graph.get(u).addAll(graph.get(v));
            graph.remove(v);
            for(Integer key : graph.keySet()) {
                while(graph.get(key).contains(v)) {
                    if(key == u) {
                        graph.get(key).remove(graph.get(key).indexOf(v));
                    }
                    else {
                        graph.get(key).add(graph.get(key).indexOf(v),u);
                        graph.get(key).remove(graph.get(key).indexOf(v));
                    }
                }
            }
        }

        return graph.get(pickRandomVertex()).size();
    }

    public int pickRandomVertex() {
        Object[] keys = graph.keySet().toArray();
        Random rn = new Random();
        int i = rn.nextInt(keys.length);
        return (int) keys[i];
    }

    public int getElement(int i, int j) {
        return graph.get(i).get(j);
    }
}
