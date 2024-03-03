package be.byteworx.challenge9;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ChallengeNine {
    public Integer[] nums = readFromInputStream(getClass().getResourceAsStream("/numbers.txt"));

    public ChallengeNine() throws IOException {
    }

    public Integer[] sortingTime() {
        long start = System.nanoTime();

        Arrays.sort(nums);

        long end = System.nanoTime();
        System.out.println("Sorting time (lower is better): " + (end - start));
        return nums;
    }

    public static Integer[] readFromInputStream(InputStream inputStream)
            throws IOException {
        ArrayList<Integer> integers = new ArrayList<>();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                for (String s : split) {
                    integers.add(Integer.parseInt(s.trim()));
                }
            }
        }
        return integers.toArray(new Integer[0]);
    }

    public Integer[] getNums() {
        return nums;
    }
}
