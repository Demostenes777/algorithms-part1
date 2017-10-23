import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Alaitz Mendiola on 06/11/2016.
 */
public class Dijkstra {
    private final static String FILE_PATH = "C:\\Users\\bgpmeala\\IdeaProjects\\algorithms5\\src\\";
    private Map<Integer, Set<Integer[]>> adjL = new HashMap<>();
    private int s = 1 ;
    private MinHeap minHeap = new MinHeap(200);

    // Vertexes processed so far
    private Set<Integer> X = new HashSet<>();

    // Computed shortest path distances
    private int[] A = new int[200];

    public Dijkstra() {
        for (int i = 0; i < A.length; i++) {
            A[i] = 1000000;
            // Initialize the Heap

        }
    }

    public void readFile(String fileName) {
        String path = FILE_PATH + fileName;
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.forEach(line -> addToAdjList(line));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addToAdjList(String line) {
        String[] vs = line.split("\\t");

        int key = Integer.parseInt(vs[0]);

        if (!adjL.containsKey(key)) {
            adjL.put(key, new HashSet<Integer[]>());
        }

        for (int i = 1; i < vs.length; i++) {
            String[] adj = vs[i].split(",");
            Integer[] adjacency = {Integer.parseInt(adj[0]), Integer.parseInt(adj[1])};
            adjL.get(key).add(adjacency);
        }
    }

    public int getFirstNode() {
        Integer[] temp =  adjL.get(1).iterator().next();
        return temp[0];
    }

    public int getFirstLength() {
        return adjL.get(1).stream().findFirst().get()[1];
    }

    public void getShortestDistance() {
        // Initialize A with
        A[s] = 1;
        X.add(s);

        // while the heap has elements
        while (minHeap.hasNext()) {
            // get Min from heap (v)

            // Add v to X

            // Remove v from Heap

            // Rearrange heap

            // A[v] = key [v]
        }




    }

    /**
     * The MinHeap class has been obtained from:
     * http://www.sanfoundry.com/java-program-implement-min-heap/
     */
    public class MinHeap
    {
        private int[] Heap;
        private int size;
        private int maxsize;

        private static final int FRONT = 1;

        public MinHeap(int maxsize)
        {
            this.maxsize = maxsize;
            this.size = 0;
            Heap = new int[this.maxsize + 1];
            Heap[0] = Integer.MIN_VALUE;
        }

        private int parent(int pos)
        {
            return pos / 2;
        }

        private int leftChild(int pos)
        {
            return (2 * pos);
        }

        private int rightChild(int pos)
        {
            return (2 * pos) + 1;
        }

        private boolean isLeaf(int pos)
        {
            if (pos >=  (size / 2)  &&  pos <= size)
            {
                return true;
            }
            return false;
        }

        private void swap(int fpos, int spos)
        {
            int tmp;
            tmp = Heap[fpos];
            Heap[fpos] = Heap[spos];
            Heap[spos] = tmp;
        }

        private void minHeapify(int pos)
        {
            if (!isLeaf(pos))
            {
                if ( Heap[pos] > Heap[leftChild(pos)]  || Heap[pos] > Heap[rightChild(pos)])
                {
                    if (Heap[leftChild(pos)] < Heap[rightChild(pos)])
                    {
                        swap(pos, leftChild(pos));
                        minHeapify(leftChild(pos));
                    }else
                    {
                        swap(pos, rightChild(pos));
                        minHeapify(rightChild(pos));
                    }
                }
            }
        }

        public void insert(int element)
        {
            Heap[++size] = element;
            int current = size;

            while (Heap[current] < Heap[parent(current)])
            {
                swap(current,parent(current));
                current = parent(current);
            }
        }

        public void minHeap()
        {
            for (int pos = (size / 2); pos >= 1 ; pos--)
            {
                minHeapify(pos);
            }
        }

        public int remove()
        {
            int popped = Heap[FRONT];
            Heap[FRONT] = Heap[size--];
            minHeapify(FRONT);
            return popped;
        }
    }
}
