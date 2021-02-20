package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.domain.Lotto.DUPLICATE_ERROR;
import static lotto.domain.Lotto.LOTTO_SIZE_ERROR;
import static lotto.domain.WinningLottoTest.createCustomWinningLotto;
import static lotto.view.InputView.convertStringsToInts;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTest {
    private Lotto lotto;

    public static Lotto createCustomLotto(String customNumbers) {
        int[] numbers = convertStringsToInts(customNumbers.split(", "));
        return new Lotto(Arrays.asList(
                new LottoNumber(numbers[0]),
                new LottoNumber(numbers[1]),
                new LottoNumber(numbers[2]),
                new LottoNumber(numbers[3]),
                new LottoNumber(numbers[4]),
                new LottoNumber(numbers[5]))
        );
    }

    @BeforeEach
    void setUp() {
        lotto = createCustomLotto("1, 2, 3, 20, 21, 40");
    }

    @DisplayName("로또의 사이즈를 제대로 검증 해주는지")
    @Test
    void validateLottoSize() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6),
                new LottoNumber(7)
        ))).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_SIZE_ERROR);
    }

    @DisplayName("로또 숫자가 중복되는 것이 있는지 제대로 검증 해주는지")
    @Test
    void validateDuplicates() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(5)
        ))).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DUPLICATE_ERROR);

    }

    @DisplayName("매칭되는 숫자를 올바로 카운트 하는 지")
    @Test
    void countMatchingNumbers() {
        WinningLotto winningLotto = createCustomWinningLotto("1, 2, 3, 4, 5, 6", "10");

        int matches = lotto.countMatchingNumbers(winningLotto);

        assertThat(matches).isEqualTo(3);
    }

    @DisplayName("보너스 숫자가 있는지 올바로 확인 하는지")
    @Test
    void hasBonusNumber() {
        WinningLotto winningLotto = createCustomWinningLotto("1, 2, 3, 4, 5, 6", "20");

        boolean match = lotto.hasBonusNumber(winningLotto);

        assertTrue(match);
    }

    @Test
    void getLottoSummary() {
        assertThat(lotto.getLottoSummary()).isEqualTo("[1, 2, 3, 20, 21, 40]");
    }
}
