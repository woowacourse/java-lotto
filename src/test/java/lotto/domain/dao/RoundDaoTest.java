package lotto.domain.dao;

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
        roundDao.addRound(1, lottoMoney);
    }

    @Test
    void 금액_확인_테스트() throws SQLException {
        int money = roundDao.findMoneyByRound(1);
        assertThat(money).isEqualTo(14_000);
    }

    @Test
    void 라운드_확인() throws SQLException {
        assertThat(roundDao.findMaxRound()).isEqualTo(9);
    }
}