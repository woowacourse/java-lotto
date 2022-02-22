package lotto;

import static org.assertj.core.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinnerLottoTest {

    @Test
    @DisplayName("숫자가 전부 일치하면 1등을 반환한다.")
    void countEqualsLottoNumbers() {
        WinnerLotto winner =
            new WinnerLotto(new Lotto(List.of(number(1), number(2), number(3), number(4), number(5), number(6))),
                number(1));
        Lotto lotto = new Lotto(List.of(number(1), number(2), number(3), number(4), number(5), number(6)));

        assertThat(winner.findRank(lotto)).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("숫자가 5개 일치하고 보너스 숫자를 포함하면 2등을 반환한다.")
    void containsNumbersAndBonusNumber() {
        Number bonus = number(7);
        WinnerLotto winner =
            new WinnerLotto(new Lotto(List.of(number(1), number(2), number(3), number(4), number(5), number(6))),
                bonus);
        Lotto lotto = new Lotto(List.of(number(1), number(2), number(3), number(4), number(5), number(7)));

        assertThat(winner.findRank(lotto)).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("숫자가 5개 일치하면 3등을 반환한다. (보너스 미포함)")
    void containsNumbersFiveReturnThirdNotContainsBonus() {
        Number bonus = number(7);
        WinnerLotto winner =
            new WinnerLotto(new Lotto(List.of(number(1), number(2), number(3), number(4), number(5), number(6))),
                bonus);
        Lotto lotto = new Lotto(List.of(number(1), number(2), number(3), number(4), number(5), number(9)));

        assertThat(winner.findRank(lotto)).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("숫자가 5개 일치하면 3등을 반환한다. (보너스 포함)")
    void containsNumbersFiveReturnThird() {
        Number bonus = number(7);
        WinnerLotto winner =
            new WinnerLotto(new Lotto(List.of(number(1), number(2), number(3), number(4), number(5), number(6))),
                bonus);
        Lotto lotto = new Lotto(List.of(number(1), number(2), number(3), number(4), number(7), number(9)));

        assertThat(winner.findRank(lotto)).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("숫자가 4개 일치하면 4등을 반환한다. (보너스 미포함)")
    void containsNumbersFourReturnFourthNotContainsBonus() {
        Number bonus = number(7);
        WinnerLotto winner =
            new WinnerLotto(new Lotto(List.of(number(1), number(2), number(3), number(4), number(5), number(6))),
                bonus);
        Lotto lotto = new Lotto(List.of(number(1), number(2), number(3), number(4), number(9), number(10)));

        assertThat(winner.findRank(lotto)).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("숫자가 4개 일치하면 4등을 반환한다. (보너스 포함)")
    void containsNumbersFourReturnFourth() {
        Number bonus = number(7);
        WinnerLotto winner =
            new WinnerLotto(new Lotto(List.of(number(1), number(2), number(3), number(4), number(5), number(6))),
                bonus);
        Lotto lotto = new Lotto(List.of(number(1), number(2), number(3), number(7), number(9), number(10)));

        assertThat(winner.findRank(lotto)).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("숫자가 3개 일치하면 5등을 반환한다. (보너스 미포함)")
    void containsNumbersThreeReturnFifthNotContainsBonus() {
        Number bonus = number(7);
        WinnerLotto winner =
            new WinnerLotto(new Lotto(List.of(number(1), number(2), number(3), number(4), number(5), number(6))),
                bonus);
        Lotto lotto = new Lotto(List.of(number(1), number(2), number(3), number(8), number(9), number(10)));

        assertThat(winner.findRank(lotto)).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("숫자가 3개 일치하면 5등을 반환한다. (보너스 포함)")
    void containsNumbersThreeReturnFifth() {
        Number bonus = number(7);
        WinnerLotto winner =
            new WinnerLotto(new Lotto(List.of(number(1), number(2), number(3), number(4), number(5), number(6))),
                bonus);
        Lotto lotto = new Lotto(List.of(number(1), number(2), number(7), number(8), number(9), number(10)));

        assertThat(winner.findRank(lotto)).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("숫자가 2개 이하로 일치하면 None을 반환한다.")
    void containsNumbersSecondReturnNone() {
        Number bonus = number(7);
        WinnerLotto winner =
            new WinnerLotto(new Lotto(List.of(number(1), number(2), number(3), number(4), number(5), number(6))),
                bonus);
        Lotto lotto = new Lotto(List.of(number(1), number(2), number(8), number(9), number(10), number(11)));

        assertThat(winner.findRank(lotto)).isEqualTo(Rank.NONE);
    }

    private Number number(int number) {
        return new Number(number);
    }
}
