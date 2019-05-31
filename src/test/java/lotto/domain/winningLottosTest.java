package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class winningLottosTest {

    @Test
    void 비교1() {
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 45);
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertEquals(2000000000, winningLotto.match(lotto));
    }

    @Test
    void 비교2() {
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 45);
        Lotto lotto = new Lotto(Arrays.asList(7, 8, 9, 10, 11, 12));

        assertEquals(0, winningLotto.match(lotto));
    }
}