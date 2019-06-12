package lotto.dao;

import lotto.DatabaseConnection;
import lotto.domain.LottoMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class RoundDaoTest {
    Connection connection;
    RoundDao roundDao;

    @BeforeEach
    void setUp() {
        connection = new DatabaseConnection().getConnection();
        roundDao = new RoundDao(connection);
    }

    @Test
    void 금액_추가_테스트() throws SQLException {
        connection.setAutoCommit(false);
        int round = 100;
        LottoMoney lottoMoney = new LottoMoney(14_000);

        roundDao.addRound(round, lottoMoney);
        assertThat(roundDao.findMoneyByRound(round)).isEqualTo(14_000);

        assertThat(roundDao.findMaxRound()).isEqualTo(round);
        roundDao.deleteMoneyByRound(round);
        assertThat(roundDao.findMoneyByRound(round)).isEqualTo(-1);
    }

    @Test
    void 라운드_확인() throws SQLException {
        connection.setAutoCommit(false);
        int round = 100;
        LottoMoney lottoMoney = new LottoMoney(14_000);
        roundDao.addRound(round, lottoMoney);

        assertThat(roundDao.findMaxRound()).isEqualTo(round);
        connection.rollback();
    }

    @Test
    void 금액_삭제_확인() throws SQLException {
        connection.setAutoCommit(false);
        int round = 100;
        LottoMoney lottoMoney = new LottoMoney(14_000);

        roundDao.addRound(round, lottoMoney);
        roundDao.deleteMoneyByRound(round);
        assertThat(roundDao.findMoneyByRound(round)).isEqualTo(-1);
        connection.rollback();
    }
}