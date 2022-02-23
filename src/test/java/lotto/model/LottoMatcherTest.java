package lotto.model;

import lotto.model.Lotto;
import lotto.model.LottoMatcher;
import lotto.model.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoMatcherTest {

    @Test
    @DisplayName("당첨 번호를 비교한다")
    void matchNumber() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoMatcher matcher = new LottoMatcher(Arrays.asList(1, 2, 3, 4, 44, 45), 6);
        int actual = matcher.matchWinningNumbers(lotto);

        assertThat(actual).isEqualTo(4);
    }

    @Test
    @DisplayName("보너스 번호 당첨 여부를 확인한다")
    void matchBonusNumberTest() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoMatcher matcher = new LottoMatcher(Arrays.asList(1, 2, 3, 4, 44, 45), 5);
        boolean actual = matcher.matchBonus(lotto);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("2등 당첨 여부를 확인한다")
    void rankTest() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoMatcher matcher = new LottoMatcher(Arrays.asList(1, 2, 3, 4, 5, 45), 6);
        Rank rank = matcher.match(lotto);

        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("3등 당첨 여부를 확인한다")
    void rankFirstTest() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 44));
        LottoMatcher matcher = new LottoMatcher(Arrays.asList(1, 2, 3, 4, 5, 6), 45);
        Rank rank = matcher.match(lotto);

        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("보너스 볼 번호와 지난주 당첨 번호가 중복된 번호인 경우 예외 처리")
    void validateDuplicateBonusBallNumberTest() {
        assertThatThrownBy(() -> {
            new LottoMatcher(Arrays.asList(1, 2, 3, 4, 5, 6), 6);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 볼 번호가 당첨 번호와 중복입니다.");
    }

    @ParameterizedTest
    @DisplayName("보너스 볼 번호의 범위가 1~45가 아닌 경우 예외 처리")
    @ValueSource(ints = {-1, 0, 46})
    void validateRangeBonusNumberTest(int bonusNumber) {
        assertThatThrownBy(() -> {
            new LottoMatcher(Arrays.asList(1, 2, 3, 4, 5, 6), bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 볼 번호가 1~45 범위 내에 해당하지 않습니다.");
    }
}