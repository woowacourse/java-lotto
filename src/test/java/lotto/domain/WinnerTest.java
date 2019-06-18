package lotto.domain;

import lotto.domain.customcreatelotto.DefaultCustomCreateCreateLotto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author heebg
 * @version 1.0 2019-06-02
 */
public class WinnerTest {
    public static final Winner winner = new Winner(LottoTest.createLotto, new LottoNumber(8));

    @Test
    void customWinLotto_확인() {
        assertThat(winner.matchLottoCount(LottoTest.createLotto)).isEqualTo(6);
    }

    @Test
    void customWinBonus_확인() {
        assertTrue(winner.matchBonus(Lotto.customLotto(Arrays.asList(1, 2, 3, 4, 5, 8), new DefaultCustomCreateCreateLotto())));
    }

    @Test
    void customWinBonus_확인_false() {
        assertFalse(winner.matchBonus(LottoTest.createLotto));
    }

    @Test
    void customWinBonus_예외_확인() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Winner(LottoTest.createLotto, new LottoNumber(6));
        });
    }
}
