package domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CounterTest {
    private static int X = 10;
    private static int Y = 11;

    @Test
    void count_아무것도넣지않음() {
        Counter<Integer> counter = Counter.create();

        assertThat(counter.count(X)).isZero();
    }

    @Test
    void count_X값확인() {
        int xCnt = 2;
        Counter<Integer> counter = createInitiatedCounter(xCnt, 3);

        assertThat(counter.count(X)).isEqualTo(xCnt);
    }

    @Test
    void count_Y값확인() {
        int yCnt = 3;
        Counter<Integer> counter = createInitiatedCounter(2, yCnt);

        assertThat(counter.count(Y)).isEqualTo(yCnt);
    }

    @Test
    void keySet_XY포함하는지() {
        Counter<Integer> counter = createInitiatedCounter(2, 1);
        List<Integer> XY = Arrays.asList(X, Y);

        assertThat(hasAll(counter.keySet(), XY)).isTrue();
    }

    private boolean hasAll(Set<Integer> keySet, List<Integer> wants) {
        for (Integer want: wants) {
            if (!keySet.contains(want)) {
                return false;
            }
        }
        return true;
    }

    private Counter createInitiatedCounter(int XCnt, int YCnt) {
        Counter counter = Counter.create();
        for (int i = 0; i < XCnt; i++) {
            counter.put(X);
        }
        for (int i = 0; i < YCnt; i++) {
            counter.put(Y);
        }
        return counter;
    }
}