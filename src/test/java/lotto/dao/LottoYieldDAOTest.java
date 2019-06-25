package lotto.dao;

import lotto.utils.DBUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoYieldDAOTest {
    private Connection connection = DBUtils.getConnection();
    private LottoRoundDAO lottoRoundDAO = new LottoRoundDAO(connection);
    private LottoYieldDAO lottoYieldDAO = new LottoYieldDAO(connection);

    @Test
    void saveLottoYield() throws SQLException {
        lottoYieldDAO.saveYield(BigDecimal.valueOf(200000000), lottoRoundDAO.totalRound());
    }

    @Test
    void inquireLottoYield() throws SQLException {
        saveLottoYield();
        assertThat(lottoYieldDAO.inquireYield(lottoRoundDAO.totalRound())).isEqualTo(BigDecimal.valueOf(200000000));
    }
}
