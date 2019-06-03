package lotto.domain;

import lotto.domain.autocreatelotto.DefaultAutoCreateLotto;
import lotto.domain.customlotto.DefaultCustomLotto;
import lotto.domain.autocreatelotto.MockAutoCreateLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author heebg
 * @version 1.0 2019-06-02
 */
class RankResultTest {
    RankResult rankResult;
    Lotteries lotteries;
    Winner winner;

    @BeforeEach
    void setUp() {
        winner = new Winner(Lotto.createLotto(new MockAutoCreateLotto()), new LottoNumber(8));
        lotteries = new Lotteries();
        lotteries.addAutoLotteries(1, new MockAutoCreateLotto());
        rankResult = new RankResult(lotteries, winner, new Money(1000));
    }

    @Test
    void addRank() {
        assertThat(rankResult.matchRankCount(Rank.FIRST)).isEqualTo(1);
    }
}