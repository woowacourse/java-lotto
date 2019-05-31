package lotto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {
    @Test
    void 리스트_원소_수정_확인() {

        List<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> cloned = new ArrayList<>(Arrays.asList(7, 8, 9, 10));
        integers.addAll(cloned);
        cloned.remove(3);

        System.out.println(integers);
        System.out.println(cloned);
    }

}
