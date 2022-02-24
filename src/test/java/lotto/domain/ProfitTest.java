package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfitTest {
    private final LottoResult lottoResult = new LottoResult();

    @BeforeEach
    void init() {
        Lotto lotto1 = new Lotto(Arrays.asList("1", "2", "3", "4", "5", "6"));
        Lotto lotto2 = new Lotto(Arrays.asList("2", "3", "4", "5", "6", "7"));
        Lotto lotto3 = new Lotto(Arrays.asList("3", "4", "5", "6", "7", "8"));
        Lottos lottos = new Lottos(Arrays.asList(lotto1, lotto2, lotto3));

        Lotto winLotto = new Lotto(Arrays.asList("1", "2", "3", "4", "5", "6"));
        Ball bonusBall = new Ball("7");
        WinningLotto winningLotto = new WinningLotto(winLotto, bonusBall);

        lottos.addMatchingCount(lottoResult, winningLotto);
    }

    @Test
    @DisplayName("수익률 계산")
    void calculate_profit() {
        Profit profit = new Profit();

        PurchaseAmount purchaseAmount = new PurchaseAmount("3000");
        int totalMoney = lottoResult.getTotalMoney(); // 2030050000

        assertEquals(profit.calculate(totalMoney, purchaseAmount), 676683.333, 0.01);
    }
}