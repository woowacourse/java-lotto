package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoTest {
    @Test
    void 일치하는_숫자_갯수를_구한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        assertThat(lotto.findMatchCount(winningLotto)).isEqualTo(5);
    }

    @Test
    void 번호_포함_여부를_확인한다() {
        Lotto lotto = new Lotto(List.of(1,2,3,4,5,6));
        assertThat(lotto.containsNumber(new LottoNumber(3)))
            .isTrue();
    }

    @Test
    void 로또_번호의_등수를_판정한다() {
        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        assertThat(winningNumbers.getRank(new Lotto(List.of(1, 2, 3, 4, 5, 6)))).isEqualTo(Rank.FIRST);
        assertThat(winningNumbers.getRank(new Lotto(List.of(1, 2, 3, 4, 5, 7)))).isEqualTo(Rank.SECOND);
        assertThat(winningNumbers.getRank(new Lotto(List.of(1, 2, 3, 4, 5, 8)))).isEqualTo(Rank.THIRD);
    }

    @Test
    void 로또_번호_개수가_6개가_아닐_경우_예외를_반환한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호가_중복될_경우_예외를_반환한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
