package lotto.dao;

import lotto.TestTableCreator;
import lotto.config.DBConnector;
import lotto.config.DataSource;
import lotto.domain.Lotto;
import lotto.domain.LottoNo;
import lotto.domain.WinningLotto;
import lotto.domain.generator.LottoNosManualGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoDaoTest {
    DBConnector dbConnector = new DBConnector(DataSource.getTestInstance());
    WinningLottoDao winningLottoDao = new WinningLottoDao(dbConnector);
    int round = 0;

    @BeforeAll
    static void createTable() {
        TestTableCreator.create();
    }

    @Test
    void findByRoundTest() {
        Lotto lotto = Lotto.of(new LottoNosManualGenerator("1,2,3,4,5,6").generate());
        LottoNo bonus = LottoNo.from(10);
        WinningLotto winningLotto = new WinningLotto(lotto, bonus);
        winningLottoDao.save(winningLotto, round);

        assertThat(winningLottoDao.findAllByRound(round)).isNotNull();
    }
}