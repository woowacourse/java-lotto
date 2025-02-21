package lotto.domain;

import static lotto.common.constant.BusinessRule.*;
import static lotto.common.constant.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningLottoTest {
    private static final int defaultBonus = 10;
    private static final Lotto defaultLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

    @Test
    @DisplayName("로또 보너스 번호는 지정된 로또 범위보다 클 경우, 예외를 발생한다.")
    void error_LottoBonusMoreThenRange() {
        var bonusOverMAX = LOTTO_MAXIMUM + 1;

        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), bonusOverMAX))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_LOTTO_NUMBER_RANGE.getMessage());

    }

    @Test
    @DisplayName("로또 보너스 번호는 지정된 로또 범위보다 작을 경우, 예외를 발생한다.")
    void error_LottoBonusLessRange() {
        var bonusLessMIN = LOTTO_MINIMUM - 1;
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), bonusLessMIN))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_LOTTO_NUMBER_RANGE.getMessage());
    }

    @Test
    @DisplayName("로또 보너스 번호가 로또의 번호와 중복될 경우, 예외를 발생한다.")
    void error_LottoBonusDuplicatedWithLotto() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, defaultBonus), defaultBonus))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_DUPLICATED_BONUS_NUMBER.getMessage());
    }

    @ParameterizedTest
    @MethodSource("matchWinningLottoTestParameters")
    @DisplayName("로또의 당첨 여부를 판단한다.")
    void test_MatchWinningLottoCorrectly(WinningLotto winningLotto, int expected) {
        MatchCount count = defaultLotto.matchCount(winningLotto);

        assertThat(count.matchCount()).isEqualTo(expected);
    }

    private static Stream<Arguments> matchWinningLottoTestParameters() {
        return Stream.of(
            Arguments.of(new WinningLotto(List.of(1, 2, 3, 4, 5, 10), 7), 5),
            Arguments.of(new WinningLotto(List.of(1, 2, 3, 4, 11, 10), 8), 4),
            Arguments.of(new WinningLotto(List.of(1, 2, 3, 12, 11, 10), 9), 3)
        );
    }
}
