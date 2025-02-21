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
    private static final WinningLotto defaultWinningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), defaultBonus);

    @Test
    @DisplayName("로또는 당첨 번호를 비교하여 자신의 당첨 개수와 보너스 여부를 반환한다.")
    void test_returnCorrect_MatchCount() {
        var notBonus = defaultBonus + 1;
        var correctCount = 5;

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, defaultBonus));
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, notBonus), defaultBonus);

        MatchCount count = lotto.matchCount(winningLotto);

        assertThat(count.matchCount()).isEqualTo(correctCount);
        assertThat(count.bonus()).isTrue();
    }

    @Test
    @DisplayName("로또 보너스 번호는 지정된 로또 범위를 벗어날 경우, 예외를 발생한다.")
    void error_WhenLottoBonusOverRange() {
        var bonusOverMAX = LOTTO_MAXIMUM + 1;
        var bonusLessMIN = LOTTO_MINIMUM - 1;

        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), bonusOverMAX))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_LOTTO_NUMBER_RANGE.getMessage());
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
    void test_MatchWinningLotto_Correctly(WinningLotto winningLotto, int expected) {
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
