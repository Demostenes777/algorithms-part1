import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by Alaitz Mendiola on 12/11/2016.
 *
 * Coursera
 * Algorithm Design and Analysis Part I.
 * Week 6 Problem 1
 *
 * The goal of this problem is to implement a variant of the 2-SUM algorithm (covered in the Week 6 lecture on hash
 * table applications).
 *
 * The file contains 1 million integers, both positive and negative (there might be some repetitions!).This is your
 * array of integers, with the ith row of the file specifying the ith entry of the array.
 *
 * Your task is to compute the number of target values t in the interval [-10000,10000] (inclusive) such that there
 * are distinct numbers x,y in the input file that satisfy x+y=t. (NOTE: ensuring distinctness requires a one-line
 * addition to the algorithm from lecture.)
 *
 * Write your numeric answer (an integer between 0 and 20001) in the space provided.
 *
 * OPTIONAL CHALLENGE: If this problem is too easy for you, try implementing your own hash table for it. For example,
 * you could compare performance under the chaining and open addressing approaches to resolving collisions.
 */
public class TwoSumAlgorithm {

    private static final String FILE_PATH = "C:\\Users\\bgpmeala\\IdeaProjects\\algorithms6\\src\\";
    private HashSet<Long> ht = new HashSet<>();

    public TwoSumAlgorithm() {
        
    }

    /**
     * Reads the file with the set of integers and creates the hash table with them.
     * @param name file name
     */
    public void readFile(String name) {
        String fileName = FILE_PATH  + name;
        
        try(Stream<String> lines = Files.lines(Paths.get(fileName))) {
            lines.forEach(x -> ht.add(Long.parseLong(x)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public int compute(long t1, long t2) {

        int result = 0;

        for (long t = t1; t <= t2; t++) {
            for (long x : ht) {
                long y = t - x;
                if (ht.contains(y) && y != x) {
                        result++;
                        break;
                }
            }
            System.out.println(t);
        }

        return result;

    }


    // Insert elements of A into a hash table (what do we do with repeated numbers?)

    // for each x look if t-x = y
    
    
}
