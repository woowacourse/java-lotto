package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PurchasedLottosTest {

    PurchasedLotto lottos;
    Lotto winningLotto;
    LottoNumber bonus;

    @BeforeEach
    void init() {
        lottos = new PurchasedLotto();
        lottos.purchase(new CustomPurchaseStrategy(Arrays.asList(new Integer[]{1, 2, 3, 43, 44, 45})));
        lottos.purchase(new CustomPurchaseStrategy(Arrays.asList(new Integer[]{1, 2, 3, 4, 44, 45})));
        lottos.purchase(new CustomPurchaseStrategy(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 45})));
        lottos.purchase(new CustomPurchaseStrategy(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 12})));

        Integer[] winningNumbersArray = {1, 2, 3, 4, 5, 12};
        List<Integer> winningNumbers = Arrays.asList(winningNumbersArray);
        winningLotto = new Lotto(winningNumbers);

        bonus = new LottoNumber(6);
    }

    @Test
    void lottos_makeRightResult() {
        Map<WinnerPrice, Integer> finalResult = lottos.calculateWinning(winningLotto, bonus);

        assertThat(finalResult.get(WinnerPrice.FIFTH)).isEqualTo(1);
        assertThat(finalResult.get(WinnerPrice.FOURTH)).isEqualTo(1);
        assertThat(finalResult.get(WinnerPrice.THIRD)).isEqualTo(1);
        assertThat(finalResult.get(WinnerPrice.SECOND)).isEqualTo(0);
        assertThat(finalResult.get(WinnerPrice.FIRST)).isEqualTo(1);
        assertThat(finalResult.get(WinnerPrice.FAIL)).isEqualTo(0);
    }


    /**
     * 맞는 결과 Map을 찾아내는지?
     * 최종 상금 계산이 옳바르게 됐는지?
     */
}
