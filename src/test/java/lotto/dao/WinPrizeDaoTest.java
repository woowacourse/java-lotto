package lotto.dao;

import lotto.TestTableCreator;
import lotto.config.DBConnector;
import lotto.config.DataSource;
import lotto.domain.Rank;
import lotto.domain.WinPrize;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class WinPrizeDaoTest {
    DBConnector dbConnector = new DBConnector(DataSource.getTestInstance());
    WinPrizeDao winPrizeDao = new WinPrizeDao(dbConnector);
    WinPrize winPrize = new WinPrize();
    int round = 0;

    @BeforeAll
    static void createTable() {
        TestTableCreator.create();
    }

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
    void saveAndFindTest() {
        winPrizeDao.save(winPrize, round);

        Optional<WinPrize> expected = winPrizeDao.findAllByRound(0);
//        System.out.println(expected.orElse(new WinPrize()).getTotalPrize());
        assertThat(expected.get().getTotalPrize()).isNotNull();
    }
}