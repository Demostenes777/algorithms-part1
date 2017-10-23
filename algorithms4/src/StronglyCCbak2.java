import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Alaitz Mendiola on 29/10/2016.
 */
public class StronglyCCbak2 {

    private final static String FILE_PATH = "C:\\Users\\bgpmeala\\IdeaProjects\\algorithms4\\src\\";
    private List<int[]> adjList = new ArrayList<>();
    private Map<Integer, Set<Integer>> graph = new HashMap<>();
    private Map<Integer, Set<Integer>> reverseGraph = new HashMap<>();
    private vertexInfo[] vInfo;
    Deque<Integer> stack = new ArrayDeque<>();
    private int numNodes;
    private int finishingTime = 0;
    private int s;

    public StronglyCCbak2(int numNodes) {
        this.numNodes = numNodes;
        vInfo = new vertexInfo[numNodes + 1];
        for (int i = 0; i < vInfo.length; i++) {
            vInfo[i] = new vertexInfo();
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



    private int[] getVertexes(String line) {
        int[] vi = new int[2];
        String[] vs = line.split(" ");
        vi[0] = Integer.parseInt(vs[0]);
        vi[1] = Integer.parseInt(vs[1]);

        return vi;
    }

    public int[] getEntry(int i) {
        return adjList.get(i);
    }

    public void dfs() {

        // run dfs-loop on Grev
        for (int i = numNodes; i > 1; i--) {
            if (!vInfo[i].explored) {
                s = i;
                dfs(i, true, reverseGraph);
            }
        }

        s = 0;
        finishingTime = 0;
        // run dfs on G
        for (int i = numNodes; i > 0; i--) {

            int v = getVertexIndex(i);
            if (vInfo[v].explored) {
                s = v;
                dfs(v, false, graph);
            }
        }


    }

    private int getVertexIndex(int j) {
        for (int i = 1; i < vInfo.length - 1; i++) {
            if (vInfo[i].getT() == j) {
                return i;
            }
        }
        return 0;
    }


    private void dfs(int i, boolean reverse, Map<Integer, Set<Integer>> graph) {
        stack.push(i);

        if (reverse) {
            while (!stack.isEmpty()) {
                int current = stack.pop();
                vInfo[current].setLeader(s);
                if (vInfo[current].explored) {
                    continue;
                }
                if (graph.containsKey(current)) {
                    for (int head : graph.get(current)) {
                        if (!vInfo[head].explored) {
                            stack.push(head);
                        }
                    }
                }
                vInfo[current].setExplored(reverse);
                finishingTime++;
                vInfo[current].setT(finishingTime);
            }
        } else {
            while (!stack.isEmpty()) {
                int current = stack.pop();
                vInfo[current].setLeader(s);
                if (!vInfo[current].explored){
                    continue;
                }
                if (graph.containsKey(current)) {
                    for (int head : graph.get(current)) {
                        if (vInfo[head].explored) {
                            stack.push(head);
                        }
                    }
                }
                vInfo[current].setExplored(reverse);
                finishingTime++;
                vInfo[current].setT(finishingTime);
            }
        }


    }

    public Stream<Integer> getSCCs() {
        Map<Integer,Integer> leaders = new HashMap<>();
        for (int i = 1; i < vInfo.length; i++) {
            if (leaders.containsKey(vInfo[i].getLeader())) {
                leaders.replace(vInfo[i].getLeader(), leaders.get(vInfo[i].getLeader()) + 1);
            }
            else {
                leaders.put(vInfo[i].getLeader(), 1);
            }
        }


        return leaders.values().stream().sorted();

    }

    private class vertexInfo {
        boolean explored = false;
        int leader;
        int t;

        public vertexInfo() {
        }

        public int getLeader() {
            return leader;
        }

        public void setLeader(int leader) {
            this.leader = leader;
        }

        public boolean isExplored() {
            return explored;
        }

        public void setExplored(boolean explored) {
            this.explored = explored;
        }

        public int getT() {
            return t;
        }

        public void setT(int t) {
            this.t = t;
        }
    }
}
