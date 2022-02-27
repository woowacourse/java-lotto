package domain;

import domain.strategy.CustomPurchaseStrategy;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PurchasedLottosTest {

    private Lottos lottos;
    private WinningNumber winningLotto;
    private int inputMoney;

    @BeforeEach
    void init() {
        inputMoney = 1400;
        lottos = new Lottos(inputMoney);
        lottos.purchase(new CustomPurchaseStrategy(Arrays.asList(1, 2, 3, 43, 44, 45)));
        lottos.purchase(new CustomPurchaseStrategy(Arrays.asList(1, 2, 3, 4, 44, 45)));
        lottos.purchase(new CustomPurchaseStrategy(Arrays.asList(1, 2, 3, 4, 5, 45)));
        lottos.purchase(new CustomPurchaseStrategy(Arrays.asList(1, 2, 3, 4, 5, 12)));

        Integer[] winningNumbersArray = {1, 2, 3, 4, 5, 12};
        List<Integer> winningNumbers = Arrays.asList(winningNumbersArray);
        winningLotto = new WinningNumber(winningNumbers);
        winningLotto.addBonusNumber(6);
    }

//    @Test
//    @DisplayName("올바른 상금이 계산된다.")
//    void lottos_makeRightTotalPrize() {
//        PrizeResult finalResult = lottos.calculatePrizeResult(winningLotto);
//
//        assertThat(finalResult.totalPrize()).isEqualTo(2001555000);
//    }

//    @Test
//    @DisplayName("올바른 수익률이 계산된다.")
//    void lottos_calculateEarningRate() {
//        lottos.calculatePrizeResult(winningLotto);
//
//        int prize = 2001555000;
//        float expected = (float) prize / inputMoney;
//        expected = (float) (Math.floor(expected * 100) / 100.0);
//
//        assertThat(lottos.calculateEarningRate()).isEqualTo(expected);
//    }

}
