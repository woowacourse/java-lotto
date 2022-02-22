package study;

import java.util.Collections;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

public class ShuffleTest {
    @Test
    void shuffle_numbers() {
        List<Object> intList = Arrays.asList(new int[]{1, 2, 3, 4, 5, 6});
        Collections.shuffle(intList);
        for (Object integer : intList) {
            System.out.println((int) integer);
        }
    }
}
