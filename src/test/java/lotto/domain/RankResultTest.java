package lotto.domain;

import lotto.domain.autocreatelotto.MockAutoCreateLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author heebg
 * @version 1.0 2019-06-02
 */
public class RankResultTest {
    private RankResult rankResultTest;
    private Lotteries lotteries;

    @BeforeEach
    void setUp() {
        lotteries = new Lotteries();
        lotteries.addAutoLotteries(1, new MockAutoCreateLotto());
        rankResultTest = new RankResult(lotteries, WinnerTest.winner, new Money(1000));
    }

    @Test
    void addRank() {
        assertThat(rankResultTest.matchRankCount(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    void rate() {
        System.out.println(String.valueOf(rankResultTest.getRate()));
    }
}