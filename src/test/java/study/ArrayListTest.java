package study;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArrayListTest {
    @Test
    void ArrayList_contains() {
        //given
        List<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> b = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<List<Integer>> arrayList = new ArrayList<>(Arrays.asList(a));

        //when & //then
        Assertions.assertThat(arrayList.contains(b)).isTrue();
    }
}
