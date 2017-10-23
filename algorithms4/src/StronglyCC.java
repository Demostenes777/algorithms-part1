import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Alaitz Mendiola on 29/10/2016.
 */
public class StronglyCC {

    private final static String FILE_PATH = "C:\\Users\\bgpmeala\\IdeaProjects\\algorithms4\\src\\";
    private Map<Integer, Set<Integer>> graph = new HashMap<>();
    private Map<Integer, Set<Integer>> reverseGraph = new HashMap<>();
    private int[] visited;
    private int[] fTime;
    private int[] leader;
    private int numNodes;
    private int finishingTime;
    private int s;

    public StronglyCC(int numNodes) {
        this.numNodes = numNodes;
        visited = new int[numNodes + 1];
        fTime = new int[numNodes + 1];
        leader = new int[numNodes + 1];

        for (int i = 0; i <= numNodes; i++) {
            visited[i] = 0;
            fTime[i] = 0;
            leader[i] = 0;
        }
    }

    public void readFile(String fileName) {
        try(Stream<String> lines = Files.lines(Paths.get(FILE_PATH + fileName))) {
            computeGraphs(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void computeGraphs(Stream<String> lines) {
        lines.forEach(line -> addToGraphs(line));
    }

    private void addToGraphs(String line) {
        String[] vs = line.split(" ");
        int i = Integer.parseInt(vs[0]);
        int j = Integer.parseInt(vs[1]);

        if (!graph.containsKey(i)) {
            graph.put(i, new HashSet<Integer>());
        }

        if (!reverseGraph.containsKey(j)) {
            reverseGraph.put(j, new HashSet<Integer>());
        }

        graph.get(i).add(j);
        reverseGraph.get(j).add(i);
    }


    public void dfs() {

        s = 0;
        finishingTime = 0;
        // run dfs-loop on Grev
        for (int i = numNodes; i > 1; i--) {
            if (visited[i] == 0) {
                s = i;
                dfs(reverseGraph, i);
            }
        }

        for (int i = 0; i <= numNodes; i++) {
            visited[i] = 0;
        }


        for (int i = numNodes; i > 1; i--) {
            int v = getVertexIndex(i);
            if (visited[v] == 0) {
                s = v;
                dfs(graph, v);
            }
        }
    }

    private int getVertexIndex(int j) {
        for (int i = 1; i < leader.length - 1; i++) {
            if (leader[i] == j) {
                return i;
            }
        }
        return 0;
    }


    private void dfs(Map<Integer, Set<Integer>> graph, int i) {

        visited[i] = 1;
        leader[i] = s;
        if (graph.containsKey(i)) {
            for (int head : reverseGraph.get(i)) {
                if (visited[head] == 0) {
                    dfs(graph, head);
                }
            }
        }
        finishingTime++;
        fTime[i] = finishingTime;
    }

    public Stream<Integer> getSCCs() {
        Map<Integer,Integer> leaders = new HashMap<>();
        for (int i = 1; i < leader.length; i++) {
            if (leaders.containsKey(leader[i])) {
                leaders.replace(leader[i], leaders.get(leader[i]) + 1);
            }
            else {
                leaders.put(leader[i], 1);
            }
        }


        return leaders.values().stream().sorted();

    }

}
