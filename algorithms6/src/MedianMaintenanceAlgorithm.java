import javafx.scene.layout.Priority;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

/**
 * Created by Alaitz Mendiola on 12/11/2016.
 *
 * Coursera
 * Algorithm Design and Analysis Part I.
 * Week 6 Problem 2
 *
 * The goal of this problem is to implement the "Median Maintenance" algorithm (covered in the Week 5 lecture on heap
 * applications). The text file contains a list of the integers from 1 to 10000 in unsorted order; you should treat
 * this as a stream of numbers, arriving one by one. Letting xi denote the ith number of the file, the kth median mk
 * is defined as the median of the numbers x1,…,xk. (So, if k is odd, then mk is ((k+1)/2)th smallest number among
 * x1,…,xk; if k is even, then mk is the (k/2)th smallest number among x1,…,xk.)
 *
 * In the box below you should type the sum of these 10000 medians, modulo 10000 (i.e., only the last 4 digits). That
 * is, you should compute (m1+m2+m3+⋯+m10000)mod10000.
 *
 * OPTIONAL EXERCISE: Compare the performance achieved by heap-based and search-tree-based implementations of the
 * algorithm.
 */
public class MedianMaintenanceAlgorithm {


    private static final String FILE_PATH = "C:\\Users\\bgpmeala\\IdeaProjects\\algorithms6\\src\\";

    private PriorityQueue<Integer> hmin = new PriorityQueue<>(10, new maxComparator());
    private PriorityQueue<Integer> hmax = new PriorityQueue<>();
    private int medians = 0;

    public MedianMaintenanceAlgorithm() {
    }


    public void readFile(String name) {
        String fileName = FILE_PATH + name;
        try(Stream<String> lines = Files.lines(Paths.get(fileName))) {
            lines.forEach(x -> insertInHeap(Integer.parseInt(x)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void insertInHeap(int i) {

        if (hmin.size() == 0) {
            hmin.add(i);
        } else {
            if (i < hmin.peek()) {
                hmin.add(i);
            }
            else {
                if (hmax.size() == 0) {
                    hmax.add(i);
                } else {
                    if (i > hmax.peek()) {
                        hmax.add(i);
                    } else {
                        if (hmin.size() > hmax.size()) {
                            hmax.add(i);
                        } else {
                            hmin.add(i);
                        }
                    }
                }
            }
        }

        if (Math.abs(hmin.size() - hmax.size()) > 1) {
            if (hmin.size() > hmax.size()) {
                hmax.add(hmin.poll());
            } else {
                hmin.add(hmax.poll());
            }
        }

        updateMedians();
    }

    private void updateMedians() {
        int sizes = hmax.size() + hmin.size();
        int m = 0;

        if (sizes % 2 != 0) {
            if (hmax.size() > hmin.size()) {
                m = hmax.peek();
            }
            else {
                m = hmin.peek();
            }

        } else {
            m = hmin.peek();
        }

        medians = medians + m;

    }

    public int getMedians() {
        return medians % 10000;
    }

    private class maxComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
}
