package util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomGenerator {

    public static List<Integer> generateNumbers(int start, int end, int count) {
        int result[] = new int[count];
        Random r = new Random();

        for (int i = 0; i < count; i++) {
            result[i] = r.nextInt(end) + start;
            for (int j = 0; j < i; j++) {
                if (result[i] == result[j]) {
                    i--;
                }
            }
        }
        Arrays.sort(result);
        return Arrays.stream(result).boxed().toList();
    }

}
