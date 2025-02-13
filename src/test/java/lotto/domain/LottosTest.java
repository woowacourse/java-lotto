package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    @DisplayName("당첨 번호가 4,5,6,7,8,9이고, 사용자가 구매한 로또가 1,2,3,4,5,6일 때 5등 당첨 확인")
    @Test
    void 로또_당첨_확인() {
        //given
        Lotto winningLotto = new Lotto("4, 5, 6, 7, 8, 9");
        String bonusNumber = "10";
        WinningLotto prizeLotto = new WinningLotto(winningLotto, bonusNumber);

        //when
        Lottos lottos = new Lottos(1, new FixedLottoGenerator());

        //then
        Assertions.assertThat(lottos.calculatePrize(prizeLotto).calculateProfit(new Money("10000")))
                .isEqualTo(0.50);

    }
}
