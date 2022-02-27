package domain;

import domain.strategy.CustomPurchaseStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("상금과 수익률을 옳바르게 계산하는지 테스트한다.")
public class LottosTestGame {

    LottoGame lottos;
    WinningLotto winningLotto;
    int inputMoney;

    @BeforeEach
    void init() {
        inputMoney = 1400;
        lottos = new LottoGame(inputMoney);
        lottos.purchase(new CustomPurchaseStrategy(List.of(1, 2, 3, 43, 44, 45)));
        lottos.purchase(new CustomPurchaseStrategy(List.of(1, 2, 3, 4, 44, 45)));
        lottos.purchase(new CustomPurchaseStrategy(List.of(1, 2, 3, 4, 5, 45)));
        lottos.purchase(new CustomPurchaseStrategy(List.of(1, 2, 3, 4, 5, 12)));

        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 12);
        winningLotto = new WinningLotto(winningNumbers, 6);
    }

    @Test
    @DisplayName("옳바른 상금이 계산된다.")
    void lottos_makeRightTotalPrize() {
        PrizeResult finalResult = lottos.calculatePrizeResult(winningLotto);

        assertThat(finalResult.totalPrize()).isEqualTo(2001555000);
    }

    @Test
    @DisplayName("옳바른 수익률이 계산된다.")
    void lottos_calculateEarningRate() {
        lottos.calculatePrizeResult(winningLotto);

        int prize = 2001555000;
        float expected = (float) prize / inputMoney;
        expected = (float) (Math.floor(expected * 100) / 100.0);

        assertThat(lottos.calculateEarningRate()).isEqualTo(expected);
    }

}
