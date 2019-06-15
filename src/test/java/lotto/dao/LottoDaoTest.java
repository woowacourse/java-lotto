package lotto.dao;

import lotto.config.DBConnector;
import lotto.config.DataSource;
import lotto.config.TableCreator;
import lotto.domain.Lotto;
import lotto.domain.generator.LottoNosManualGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LottoDaoTest {
    LottoDao lottoDao = new LottoDao(new DBConnector(DataSource.getTestInstance()));
    int round = 0;

    @BeforeAll
    static void createTable() throws Exception {
        TableCreator.create();
    }

    @BeforeEach
    void setUp() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(Lotto.of(new LottoNosManualGenerator("1,2,3,4,5,6").generate()));
        lottos.add(Lotto.of(new LottoNosManualGenerator("1,2,3,4,12,19").generate()));
        lottoDao.add(lottos, round);
    }

    @Test
    void findAllByRoundTest() {
        List<Lotto> expected = lottoDao.findAllByRound(round);
        assertThat(0).isNotEqualTo(expected.size());

    }
}