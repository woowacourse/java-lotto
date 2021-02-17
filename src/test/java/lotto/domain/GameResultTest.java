package lotto.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {

    static GameResult gameResult = new GameResult();


    @BeforeAll
    static void beforeAll() {
        gameResult.add(Rank.rankOf(4, false));
        gameResult.add(Rank.rankOf(3, false));
        gameResult.add(Rank.rankOf(2, false));
        gameResult.add(Rank.rankOf(1, false));
        gameResult.add(Rank.rankOf(0, false));
    }

    @Test
    @DisplayName("로 당첨에 따른 총 상금 테스트")
    void testTotalReward() {
        assertThat(gameResult.totalReward()).isEqualTo(55000);
    }

    @Test
    @DisplayName("수익률 테스트")
    void testProfit() {
        assertThat(gameResult.calculateProfit()).isEqualTo(11);
    }

    @Test
    @DisplayName("등수 별 당첨 갯수 테스트")
    void testCountByRank() {
        assertThat(gameResult.countByRank(Rank.FOURTH)).isEqualTo(1);
    }
}
