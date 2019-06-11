package lotto.dao;

import lotto.DatabaseConnection;
import lotto.domain.LottoMoney;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class RoundDaoTest {
    Connection connection = new DatabaseConnection().getConnection();
    RoundDao roundDao = new RoundDao(connection);

    @Test
    void 금액_추가_테스트() throws SQLException {
        LottoMoney lottoMoney = new LottoMoney(14_000);
        int round = 100;
        roundDao.addRound(round, lottoMoney);
        assertThat(roundDao.findMoneyByRound(round)).isEqualTo(14_000);
        assertThat(roundDao.findMaxRound()).isEqualTo(round);
        roundDao.deleteMoneyByRound(round);
        assertThat(roundDao.findMoneyByRound(round)).isEqualTo(-1);
    }
}