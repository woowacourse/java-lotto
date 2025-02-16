package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PrizesTest {

    @Test
    void 수익_계산_검증() {
        //given
        RandomNumber fixedRandomNumber = new FixedRandomNumber();
        Lottos lottos = new Lottos(1, fixedRandomNumber);
        Lotto winningLotto = new Lotto(new LottoNumbers(new FixedRandomNumber()));
        LottoNumber bonusNumber = new LottoNumber(7);
        Money money = new Money("1000");

        //when
        Prizes prizes = lottos.calculatePrize(winningLotto, bonusNumber);

        //then
        Assertions.assertThat(prizes.calculateProfit(money)).isEqualTo(2000000.0);
    }
}