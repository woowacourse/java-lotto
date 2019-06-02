package lotto.domain;

import lotto.domain.customlotto.DefaultCustomLotto;
import lotto.domain.makeuplotto.MockCreateLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author heebg
 * @version 1.0 2019-06-02
 */
public class WinnerTest {
    Winner winner;
    Lotto lotto;

    @BeforeEach
    void setUp() {
        winner = new Winner();
        winner.setCustomLotto(new DefaultCustomLotto());
        lotto = new Lotto();
        lotto.setCreateLotto(new MockCreateLotto());
        lotto = lotto.createLotto();
        winner.customWinLotto(Arrays.asList(1,2,3,4,5,8));
    }

    @Test
    void customWinLotto_확인() {
        assertThat(winner.matchLottoCount(lotto)).isEqualTo(5);
    }

    @Test
    void customWinBonus_확인() {
        winner.customWinBonus(6);
        assertTrue(winner.matchBonus(lotto));
    }

    @Test
    void customWinBonus_확인_false() {
        winner.customWinBonus(21);
        assertFalse(winner.matchBonus(lotto));
    }

    @Test
    void customWinBonus_예외_확인() {
        assertThrows(IllegalArgumentException.class, () -> {
            winner.customWinBonus(1);
        });
    }
}
