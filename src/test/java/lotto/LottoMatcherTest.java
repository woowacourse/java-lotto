package lotto;

import lotto.model.Lotto;
import lotto.model.LottoMatcher;
import lotto.model.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
