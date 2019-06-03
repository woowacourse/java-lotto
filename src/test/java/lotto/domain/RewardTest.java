package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RewardTest {
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 45);
    }

    @Test
    void 금액1() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(Reward.FIRST, winningLotto.match(lotto));
    }

    @Test
    void 금액2() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7));
        assertEquals(Reward.THIRD, winningLotto.match(lotto));
    }

    @Test
    void 금액3() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 8, 7));
        assertEquals(Reward.FOURTH, winningLotto.match(lotto));
    }

    @Test
    void 금액4() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 9, 8, 7));
        assertEquals(Reward.FIFTH, winningLotto.match(lotto));
    }

    @Test
    void 금액5() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 10, 9, 8, 7));
        assertEquals(Reward.LOSE, winningLotto.match(lotto));
    }

    @Test
    void 금액6() {
        Lotto lotto = new Lotto(Arrays.asList(1, 11, 10, 9, 8, 7));
        assertEquals(Reward.LOSE, winningLotto.match(lotto));
    }

    @Test
    void 금액7() {
        Lotto lotto = new Lotto(Arrays.asList(12, 11, 10, 9, 8, 7));
        assertEquals(Reward.LOSE, winningLotto.match(lotto));
    }

    @Test
    void 금액8() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 45));
        assertEquals(Reward.SECOND, winningLotto.match(lotto));
    }
}