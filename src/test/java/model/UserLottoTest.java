package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserLottoTest {

    @Test
    @DisplayName("맞은 로또 번호의 개수를 반환한다.")
    void 맞은_로또_번호의_개수를_반환한다() {
        Set<Integer> lottoNumbers = Set.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(lottoNumbers);
        Set<Integer> winningNumbers = Set.of(1, 2, 3, 4, 5, 7);
        BonusNumber bonusNumber = new BonusNumber(40);
        UserLotto userLotto = new UserLotto(winningNumbers, bonusNumber);

        assertThat(userLotto.calculateMatchCount(lotto)).isEqualTo(5);
    }

}
