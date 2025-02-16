package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WinningNumbersTest {
    @Test
    void 당첨_결과를_구한다() {
        Lotto lotto = new Lotto(List.of(1,2,3,4,5,6));
        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(List.of(1,2,3,4,5,7)), 6);
        assertThat(winningNumbers.getRank(lotto)).isEqualTo(Rank.SECOND);
    }

    @Test
    void 당첨_번호와_보너스_번호가_중복될_경우_예외를_반환한다() {
        assertThatThrownBy(() -> new WinningNumbers(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 5))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
