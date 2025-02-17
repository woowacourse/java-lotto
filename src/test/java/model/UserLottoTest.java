package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserLottoTest {


    @Test
    @DisplayName("맞은 로또 번호의 개수를 반환한다.")
    void 맞은_로또_번호의_개수를_반환한다() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(new TestLottoNumberGenerator(lottoNumbers));
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 7);
        BonusNumber bonusNumber = new BonusNumber(40);

        UserLotto userLotto = new UserLotto(winningNumbers, bonusNumber);

        assertThat(userLotto.calculateMatchCount(lotto)).isEqualTo(5);
    }

    @Test
    @DisplayName("보너스 번호와 당첨 번호가 중복되면 예외가 발생한다.")
    void 보너스_번호와_당첨_번호가_중복되면_예외가_발생한다() {
        BonusNumber bonusNumber = new BonusNumber(1);
        assertThatThrownBy(() -> new UserLotto(List.of(1, 2, 3, 4, 5, 6), bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호와 당첨 번호는 중복되어서는 안됩니다.");
    }
}
