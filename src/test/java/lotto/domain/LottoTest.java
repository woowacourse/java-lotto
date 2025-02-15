package lotto.domain;

import static lotto.common.constant.Constant.*;
import static lotto.common.constant.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("로또 번호는 로또가 생성할 때, 정렬된다.")
    void LottoNumberSortedWhenLottoGenerate() {
        ArrayList<Integer> list = new ArrayList<>(List.of(6, 5, 4, 3, 2, 1));
        Lotto lotto = new Lotto(list);

        assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    @Test
    @DisplayName("로또 번호의 갯수가 기준(6)과 다를 경우, 예외를 발생시킨다.")
    void LottoNumberErrorWhenUncorrectedSize() {
        assertThatThrownBy(() -> new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5))))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_INCORRECT_LOTTO_SIZE.getMessage());
    }

    @Test
    @DisplayName("로또 번호의 범위가 기준에서 벗어났을 경우, 예외를 발생시킨다.")
    void LottoNumberErrorWhenOverRange() {
        var test_MAX = LOTTO_MAXIMUM + 1;
        var test_MIN = LOTTO_MAXIMUM + 2;

        assertThatThrownBy(() -> new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, test_MAX))))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_LOTTO_NUMBER_RANGE.getMessage());

        assertThatThrownBy(() -> new Lotto(new ArrayList<>(List.of(test_MIN, 2, 3, 4, 5, 6))))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_LOTTO_NUMBER_RANGE.getMessage());
    }

    @Test
    @DisplayName("로또는 당첨 번호를 비교하여 자신의 당첨 개수와 보너스 여부를 반환한다.")
    void returnCorrectMatchCount() {
        var bonus = 6;
        var notBonus = bonus + 1;
        var correctCount = 5;

        Lotto lotto = new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, bonus)));
        Lotto matchLotto = new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, notBonus)));

        MatchCount count = lotto.matchCount(matchLotto, bonus);

        assertThat(count.matchCount()).isEqualTo(correctCount);
        assertThat(count.bonus()).isTrue();
    }

}
