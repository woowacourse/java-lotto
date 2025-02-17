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

class LottoTest {
    private static final Lotto defaultLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    private static final int defaultBonus = 10;

    @Test
    @DisplayName("로또 번호는 로또가 생성할 때, 정렬된다.")
    void test_LottoNumberSorted_WhenLottoGenerate() {
        Lotto lotto = new Lotto(List.of(6, 5, 4, 3, 2, 1));

        assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    @Test
    @DisplayName("로또 번호의 갯수가 기준(6)과 다를 경우, 예외를 발생시킨다.")
    void error_WhenLottoNumberUncorrectedSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_INCORRECT_LOTTO_SIZE.getMessage());
    }

    @Test
    @DisplayName("로또 번호의 범위가 기준에서 벗어났을 경우, 예외를 발생시킨다.")
    void error_WhenLottoNumberOverRange() {
        var overMAX = LOTTO_MAXIMUM + 1;
        var lessMIN = LOTTO_MINIMUM - 1;

        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, overMAX)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_LOTTO_NUMBER_RANGE.getMessage());

        assertThatThrownBy(() -> new Lotto(List.of(lessMIN, 2, 3, 4, 5, 6)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_LOTTO_NUMBER_RANGE.getMessage());
    }

    @Test
    @DisplayName("로또는 당첨 번호를 비교하여 자신의 당첨 개수와 보너스 여부를 반환한다.")
    void test_returnCorrect_MatchCount() {
        var notBonus = defaultBonus + 1;
        var correctCount = 5;

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, defaultBonus));
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, notBonus));

        MatchCount count = lotto.matchCount(winningLotto, defaultBonus);

        assertThat(count.matchCount()).isEqualTo(correctCount);
        assertThat(count.bonus()).isTrue();
    }

    @Test
    @DisplayName("로또 보너스 번호는 지정된 로또 범위를 벗어날 경우, 예외를 발생한다.")
    void error_WhenLottoBonusOverRange() {
        var bonusOverMAX = LOTTO_MAXIMUM + 1;
        var bonusLessMIN = LOTTO_MINIMUM - 1;

        assertThatThrownBy(() -> defaultLotto.validateBonus(bonusOverMAX))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_LOTTO_NUMBER_RANGE.getMessage());
        assertThatThrownBy(() -> defaultLotto.validateBonus(bonusLessMIN))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_LOTTO_NUMBER_RANGE.getMessage());
    }

    @Test
    @DisplayName("로또 보너스 번호가 로또의 번호와 중복될 경우, 예외를 발생한다.")
    void error_LottoBonusDuplicatedWithLotto() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, defaultBonus));

        assertThatThrownBy(() -> lotto.validateBonus(defaultBonus))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_DUPLICATED_BONUS_NUMBER.getMessage());
    }

    @ParameterizedTest
    @MethodSource("matchWinningLottoTestParameters")
    @DisplayName("로또 보너스 번호가 로또의 번호와 중복될 경우, 예외를 발생한다.")
    void test_MatchWinningLotto_Correctly(Lotto winningLotto, int expected) {
        MatchCount count = defaultLotto.matchCount(winningLotto, defaultBonus);

        assertThat(count.matchCount()).isEqualTo(expected);
    }

    private static Stream<Arguments> matchWinningLottoTestParameters() {
        return Stream.of(
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 10)), 5),
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 11, 10)), 4),
            Arguments.of(new Lotto(List.of(1, 2, 3, 12, 11, 10)), 3)
        );
    }
}
