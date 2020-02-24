package lotto.domain;

import lotto.domain.result.GameResult;
import lotto.domain.result.Rank;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class gameResultTest {
    GameResult gameResult;
    public static final double PROFIT = 5000 * 3 + 30_000_000 * 2 + 2_000_000_000;

    @BeforeEach
    void initGameResult() {
        gameResult = new GameResult(Arrays.asList(Rank.values()));
        gameResult.count(Rank.THREE);
        gameResult.count(Rank.THREE);
        gameResult.count(Rank.THREE);
        gameResult.count(Rank.BONUS);
        gameResult.count(Rank.BONUS);
        gameResult.count(Rank.SIX);
    }

    @Test
    @DisplayName("게임결과 Count가 잘 증가 되는지 테스트")
    void countTest() {
        assertThat(gameResult.getCountByRank(Rank.THREE).getCount()).isEqualTo(3);
        assertThat(gameResult.getCountByRank(Rank.BONUS).getCount()).isEqualTo(2);
        assertThat(gameResult.getCountByRank(Rank.SIX).getCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률을 잘 계산하는지 테스트")
    void calculateProfitTest() {
        PurchaseMoney money = new PurchaseMoney("6000");
        gameResult.calculateProfit(money);
        assertThat(gameResult.getProfit()).isEqualTo(PROFIT / 6000 * 100);
    }
}
