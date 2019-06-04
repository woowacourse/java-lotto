package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {
    private LottoFactory lottoFactory;

    @BeforeEach
    public void setUp() {
        lottoFactory = new LottoFactory();
    }

    @Test
    public void 구매를_제대로하고_당첨로또에_따른_결과를_제대로_반환해주는지() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);   // BUY
        List<Integer> fifthNumbers = Arrays.asList(42, 2, 3, 4, 10, 11);  //FIFTH
        List<Integer> missNumbers = Arrays.asList(1, 2, 17, 18, 19, 20);  //MISS

        final int money = 2000;
        LottoService buyer = new LottoService(money);

        buyer.buy(fifthNumbers);
        while (buyer.canBuy()) {
            buyer.buy(missNumbers);
        }

        int bonusNumber = 11;
        Lotto lotto = lottoFactory.create(winningNumbers);

        LottoGameResult gameResult = buyer.gameResultOf(WinningLotto.of(lotto, LottoNumber.of(bonusNumber)));
        assertThat(gameResult.profit(LottoMachine.LOTTO_MONEY)).isEqualTo(250);
    }
}