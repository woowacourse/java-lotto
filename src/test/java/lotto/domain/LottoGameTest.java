package lotto.domain;

import lotto.utils.AutoLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameTest {

    @Test
    @DisplayName("돈에 따른 로또 구입 개수 테스트")
    void testBuyLottos() {
        LottoGame lottoGame = new LottoGame();
        Money money = new Money("1000");
        lottoGame.buyLottos(money, new AutoLottoGenerator());
        assertThat(lottoGame.myLottos().lottos().size()).isEqualTo(1);
    }
}