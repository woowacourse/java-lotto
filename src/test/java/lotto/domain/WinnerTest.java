package lotto.domain;

import lotto.domain.customlotto.DefaultCustomLotto;
import lotto.domain.autocreatelotto.MockAutoCreateLotto;
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
        winner = new Winner(Lotto.createLotto(new MockAutoCreateLotto()), new LottoNumber(8));
        lotto = Lotto.createLotto(new MockAutoCreateLotto());
    }

    @Test
    void customWinLotto_확인() {
        assertThat(winner.matchLottoCount(lotto)).isEqualTo(6);
    }

    @Test
    void customWinBonus_확인() {
        assertTrue(winner.matchBonus(Lotto.customLotto(Arrays.asList(1,2,3,4,5,8), new DefaultCustomLotto())));
    }

    @Test
    void customWinBonus_확인_false() {
        assertFalse(winner.matchBonus(lotto));
    }

    @Test
    void customWinBonus_예외_확인() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Winner(Lotto.createLotto(new MockAutoCreateLotto()), new LottoNumber(6));
        });
    }
}
