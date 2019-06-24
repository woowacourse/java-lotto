package lotto.dao;

import lotto.TestTableCreator;
import lotto.config.DBConnector;
import lotto.config.DataSource;
import lotto.domain.Lotto;
import lotto.domain.generator.LottoNosManualGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoDaoTest {
    DBConnector dbConnector = new DBConnector(DataSource.getTestInstance());
    LottoDao lottoDao = new LottoDao(dbConnector);
    List<Lotto> lottos = new ArrayList<>();
    int round = 0;


    @BeforeAll
    static void createTable() {
        TestTableCreator.create();
    }

    @BeforeEach
    void setUp() {
        lottos.add(Lotto.of(new LottoNosManualGenerator("1,2,3,4,5,6").generate()));
        lottos.add(Lotto.of(new LottoNosManualGenerator("1,2,3,4,12,19").generate()));
        lottoDao.add(lottos, round);
    }

    @Test
    void findAllByRoundTest() {
        List<Lotto> expected = lottoDao.findAllByRound(round);
        assertThat(lottos.size()).isEqualTo(expected.size());

    }
}