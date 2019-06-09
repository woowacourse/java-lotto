package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
    @Test
    void validateNoDuplication() {
        assertThrows(DuplicatedLottoNumbersException.class, () ->
                Lotto.create(new ManualLottoNumberGenerator(Arrays.asList(1, 1, 3, 4, 5, 6))));
    }

    @Test
    void validateNumOfLottoNumbers() {
        assertThrows(InvalidSizeOfLottoNumbersException.class, () ->
                Lotto.create(new ManualLottoNumberGenerator(Arrays.asList(1, 2, 3, 4, 5))));
    }

    @Test
    void containTest() {
        Lotto lotto = Lotto.create(new ManualLottoNumberGenerator(Arrays.asList(1, 2, 3, 4, 5, 6)));
        assertThat(lotto.contains(LottoNumber.valueOf(1))).isTrue();
        assertThat(lotto.contains(LottoNumber.valueOf(7))).isFalse();
    }

    @Test
    void countMatchesTest() {
        Lotto lotto = Lotto.create(new ManualLottoNumberGenerator(Arrays.asList(1, 2, 3, 4, 5, 6)));
        assertThat(lotto.countMatches(Lotto.create(new ManualLottoNumberGenerator(Arrays.asList(1, 2, 3, 4, 5, 6))))).isEqualTo(6);
        assertThat(lotto.countMatches(Lotto.create(new ManualLottoNumberGenerator(Arrays.asList(1, 2, 3, 4, 5, 7))))).isEqualTo(5);
    }

    @Test
    void createLottoTest() {
        Lotto lotto = Lotto.create(() -> Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2),
                LottoNumber.valueOf(3), LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));
    }
}
