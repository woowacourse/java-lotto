package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.vo.Number;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinnerLottoTest {
    private static final Number BONUS = new Number(7);
    private static final Lotto LOTTO = new Lotto(givenNumbers(1, 2, 3, 4, 5, 6));
    private static final WinnerLotto WINNER_LOTTO = new WinnerLotto(LOTTO, BONUS);

    @Test
    @DisplayName("숫자가 전부 일치하면 1등을 반환한다.")
    void countEqualsLottoNumbers() {
        assertThat(WINNER_LOTTO.findRank(LOTTO)).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("숫자가 5개 일치하고 보너스 숫자를 포함하면 2등을 반환한다.")
    void containsNumbersAndBonusNumber() {
        Lotto lotto = new Lotto(givenNumbers(1, 2, 3, 4, 5, 7));

        assertThat(WINNER_LOTTO.findRank(lotto)).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("숫자가 5개 일치하면 3등을 반환한다. (보너스 미포함)")
    void containsNumbersFiveReturnThirdNotContainsBonus() {
        Lotto lotto = new Lotto(givenNumbers(1, 2, 3, 4, 5, 9));

        assertThat(WINNER_LOTTO.findRank(lotto)).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("숫자가 5개 일치하면 3등을 반환한다. (보너스 포함)")
    void containsNumbersFiveReturnThird() {
        Lotto lotto = new Lotto(givenNumbers(1, 2, 3, 4, 7, 9));

        assertThat(WINNER_LOTTO.findRank(lotto)).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("숫자가 4개 일치하면 4등을 반환한다. (보너스 미포함)")
    void containsNumbersFourReturnFourthNotContainsBonus() {
        Lotto lotto = new Lotto(givenNumbers(1, 2, 3, 4, 9, 10));

        assertThat(WINNER_LOTTO.findRank(lotto)).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("숫자가 4개 일치하면 4등을 반환한다. (보너스 포함)")
    void containsNumbersFourReturnFourth() {
        Lotto lotto = new Lotto(givenNumbers(1, 2, 3, 7, 9, 10));

        assertThat(WINNER_LOTTO.findRank(lotto)).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("숫자가 3개 일치하면 5등을 반환한다. (보너스 미포함)")
    void containsNumbersThreeReturnFifthNotContainsBonus() {
        Lotto lotto = new Lotto(givenNumbers(1, 2, 3, 8, 9, 10));

        assertThat(WINNER_LOTTO.findRank(lotto)).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("숫자가 3개 일치하면 5등을 반환한다. (보너스 포함)")
    void containsNumbersThreeReturnFifth() {
        Lotto lotto = new Lotto(givenNumbers(1, 2, 7, 8, 9, 10));

        assertThat(WINNER_LOTTO.findRank(lotto)).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("숫자가 2개 이하로 일치하면 None을 반환한다.")
    void containsNumbersSecondReturnNone() {
        Lotto lotto = new Lotto(givenNumbers(1, 2, 8, 9, 10, 11));

        assertThat(WINNER_LOTTO.findRank(lotto)).isEqualTo(Rank.NONE);
    }

    @Test
    @DisplayName("보너스볼은 당첨 번호와 중복되면 예외를 발생한다.")
    void throwExceptionWhenDuplicated() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new WinnerLotto(LOTTO, new Number(6)))
            .withMessageMatching("보너스볼은 당첨번호와 중복될 수 없다.");
    }

    private static List<Number> givenNumbers(int... numbers) {
        return Arrays.stream(numbers)
            .mapToObj(Number::new)
            .collect(Collectors.toList());
    }
}
