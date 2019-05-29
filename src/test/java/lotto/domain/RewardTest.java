package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RewardTest {
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    void 금액1() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(2000000000, Reward.valueOf(winningLotto.match(lotto)).money());
    }

    @Test
    void 금액2() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7));
        assertEquals(1500000, Reward.valueOf(winningLotto.match(lotto)).money());
    }

    @Test
    void 금액3() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 8, 7));
        assertEquals(50000, Reward.valueOf(winningLotto.match(lotto)).money());
    }

    @Test
    void 금액4() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 9, 8, 7));
        assertEquals(5000, Reward.valueOf(winningLotto.match(lotto)).money());
    }

    @Test
    void 금액5() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 10, 9, 8, 7));
        assertEquals(0, Reward.valueOf(winningLotto.match(lotto)).money());
    }

    @Test
    void 금액6() {
        Lotto lotto = new Lotto(Arrays.asList(1, 11, 10, 9, 8, 7));
        assertEquals(0, Reward.valueOf(winningLotto.match(lotto)).money());
    }

    @Test
    void 금액7() {
        Lotto lotto = new Lotto(Arrays.asList(12, 11, 10, 9, 8, 7));
        assertEquals(0, Reward.valueOf(winningLotto.match(lotto)).money());
    }
}