package lotto.domain;

import lotto.domain.exceptions.InvalidBonusBallException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    @Test
    void validateLottoNumbersDoesNotContainBonusNumber() {
        assertThrows(InvalidBonusBallException.class, () -> new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 1));
    }

    @Test
    void matchTest() {
        WinningLotto win = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        assertThat(win.match(Lotto.create(new ManualLottoNumbersGenerator(Arrays.asList(1, 2, 3, 4, 5, 6))))).isEqualTo(Rank.FIRST);
        assertThat(win.match(Lotto.create(new ManualLottoNumbersGenerator(Arrays.asList(1, 2, 3, 4, 5, 7))))).isEqualTo(Rank.SECOND);
        assertThat(win.match(Lotto.create(new ManualLottoNumbersGenerator(Arrays.asList(1, 2, 3, 4, 5, 8))))).isEqualTo(Rank.THIRD);
        assertThat(win.match(Lotto.create(new ManualLottoNumbersGenerator(Arrays.asList(1, 2, 3, 4, 7, 8))))).isEqualTo(Rank.FOURTH);
        assertThat(win.match(Lotto.create(new ManualLottoNumbersGenerator(Arrays.asList(1, 2, 3, 7, 8, 9))))).isEqualTo(Rank.FIFTH);
        assertThat(win.match(Lotto.create(new ManualLottoNumbersGenerator(Arrays.asList(1, 2, 7, 8, 9, 10))))).isEqualTo(Rank.MISS);
    }
}
