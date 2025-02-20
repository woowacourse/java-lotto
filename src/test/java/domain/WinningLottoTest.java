package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoTest {

    @DisplayName("당첨 번호에 해당 번호가 포함이면 true을 리턴한다")
    @Test
    void 당첨_번호에_해당_번호가_포함이면_true을_리턴한다() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        assertThat(winningLotto.containsNumber(1)).isEqualTo(true);
        assertThat(winningLotto.containsNumber(7)).isEqualTo(false);
        assertThat(winningLotto.containsNumber(8)).isEqualTo(false);
    }

    @DisplayName("List에 보너스 볼을 포함하면 true을 리턴한다")
    @Test
    void List에_보너스_볼을_포함하면_true을_리턴한다() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        List<Integer> hasBonusBall = List.of(1, 2, 3, 4, 5, 7);
        List<Integer> hasNoBonusBall = List.of(1, 2, 3, 4, 5, 6);

        assertThat(winningLotto.hasBonusBall(hasBonusBall)).isEqualTo(true);
        assertThat(winningLotto.hasBonusBall(hasNoBonusBall)).isEqualTo(false);
    }
}
