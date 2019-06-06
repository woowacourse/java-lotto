package lotto.dao;

import lotto.domain.Rank;
import lotto.domain.WinPrize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WinPrizeDaoTest {
    WinPrize winPrize = new WinPrize();
    WinPrizeDao winPrizeDao = new WinPrizeDao();
    int round = 0;

    @BeforeEach
    void setUp() {
        winPrize.put(Rank.FIRST, 5);
        winPrize.put(Rank.SECOND, 3);
        winPrize.put(Rank.THIRD, 8);
        winPrize.put(Rank.FOURTH, 2);
        winPrize.put(Rank.FIFTH, 2);
        winPrize.put(Rank.MISS, 2);
    }

    @Test
    void add() {
        assertThat(1).isEqualTo(winPrizeDao.add(winPrize, round));
    }

    @Test
    void findByRound() {
        WinPrize expected = winPrizeDao.findByRound(round);
        assertThat(expected).isNotNull();
    }
}