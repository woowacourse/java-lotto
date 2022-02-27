package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfitTest {
    private final LottoResult lottoResult = new LottoResult();

    @BeforeEach
    void init() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(2, 3, 4, 5, 6, 7));
        Lotto lotto3 = new Lotto(List.of(3, 4, 5, 6, 7, 8));
        Lottos lottos = new Lottos(Arrays.asList(lotto1, lotto2, lotto3));

        Lotto winLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusBall = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winLotto, bonusBall);

        lottoResult.addMatchingCount(lottos, winningLotto);
    }

    @Test
    @DisplayName("수익률 계산")
    void calculate_profit_rate() {
        Money purchaseAmount = new Money(3000);
        Profit profit = lottoResult.getProfit();

        assertEquals(profit.calculateProfitRate(purchaseAmount), 676683.333, 0.01);
    }
}
