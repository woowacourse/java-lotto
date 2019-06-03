package lotto.domain;

import lotto.domain.customlotto.DefaultCustomLotto;
import lotto.domain.makeuplotto.MockCreateLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author heebg
 * @version 1.0 2019-06-02
 */
class RankResultTest {
    RankResult rankResult;
    Lotteries lotteries;
    Lotto lotto = new Lotto();
    Winner winner = new Winner();

    @BeforeEach
    void setUp() {
        lotto.setCreateLotto(new MockCreateLotto());
        lotto.createLotto();
        lotteries = new Lotteries(lotto);
        lotteries.addNewLotteries(1);
        winner.setCustomLotto(new DefaultCustomLotto());
        winner.customWinLotto(Arrays.asList(1,2,3,9,8,7));
        winner.customWinBonus(4);
        rankResult = new RankResult(lotteries, winner, new Money(1000));
    }

    @Test
    void addRank() {
        assertThat(rankResult.matchRankCount(Rank.FIFTH)).isEqualTo(1);
    }

    @Test
    void toString1() {
    }
}