package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @DisplayName("보너스 번호가 범위를 벗어나면 예외가 발생한다.")
    @Test
    void 보너스_번호가_범위를_벗어나면_예외가_발생한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 46;

        assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또는 1 이상 45 이하만 가능합니다.");
    }

    @DisplayName("당첨 번호와 보너스 번호가 중복되면 예외가 발생한다.")
    @Test
    void 당첨_번호와_보너스_번호가_중복되면_예외가_발생한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 1;

        assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호와 보너스 번호는 중복될 수 없습니다.");
    }

    @DisplayName("로또 번호를 알려주면 당첨 등수를 계산해준다.")
    @Test
    void 로또_번호를_알려주면_당첨_등수를_계산해준다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        Lottos lottos = new Lottos();
        lottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 7)));

        assertThat(winningLotto.calculateWinning(lottos).getResponses())
                .extracting("matchingCount", "hasBonus", "winningCount")
                .contains(tuple(5, true, 1));
    }

}
