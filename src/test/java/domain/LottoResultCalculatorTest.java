package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultCalculatorTest {
    private Map<Rank, Integer> calculateResult;

    @BeforeEach
    void setUp() {
        WinningInfo winningInfo = WinningInfo.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        List<Lotto> lottoBundle = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),
                new Lotto(List.of(1, 2, 3, 4, 8, 9)),
                new Lotto(List.of(1, 2, 3, 8, 9, 10)),
                new Lotto(List.of(1, 2, 8, 9, 10, 11))
        );
        calculateResult = LottoResultCalculator.calculateMatchingRank(winningInfo, lottoBundle);
    }

    @DisplayName("각 등수별 일치 수를 정확히 카운트한다.")
    @Test
    void 등수별_일치_수_판단() {
        assertEquals(1, calculateResult.get(Rank.FIRST));
        assertEquals(1, calculateResult.get(Rank.SECOND));
        assertEquals(1, calculateResult.get(Rank.THIRD));
        assertEquals(1, calculateResult.get(Rank.FOURTH));
        assertEquals(1, calculateResult.get(Rank.FIFTH));
        assertEquals(1, calculateResult.get(Rank.NONE));
    }

    @DisplayName("수익률을 정확히 계산한다.")
    @Test
    void 수익률을_정확히_계산() {
        int purchaseAmount = 6000;
        double profit = LottoResultCalculator.calculateProfit(calculateResult, purchaseAmount);
        double totalPrize =
                Rank.FIRST.getPrize() + Rank.SECOND.getPrize() + Rank.THIRD.getPrize() + Rank.FOURTH.getPrize()
                        + Rank.FIFTH.getPrize();
        double expectedProfit = Math.floor((totalPrize / purchaseAmount) * 100) / 100;

        assertEquals(expectedProfit, profit);
    }
}
