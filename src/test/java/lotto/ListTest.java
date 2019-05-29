package lotto;

import org.junit.jupiter.api.Test;

import java.util.*;

public class ListTest {
    @Test
    void 리스트_원소_수정_확인() {

        List<Integer> integers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
        List<Integer> cloned = new ArrayList<>(integers);
        cloned.remove(0);

        System.out.println(integers);
        System.out.println(cloned);
    }

}
