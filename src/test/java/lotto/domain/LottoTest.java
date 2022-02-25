package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("당첨 번호와 보너스 번호를 이용해서 당첨 등급 검색")
    void getRank() {
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");
        BonusNumber bonusNumber = new BonusNumber("7",winningLotto);
        Lotto lotto = Lotto.generateLottoByManual("1,2,3,4,5,7");
        Assertions.assertThat(lotto.getRank(winningLotto, bonusNumber)).isEqualTo(Rank.RANK_2);
    }
}
