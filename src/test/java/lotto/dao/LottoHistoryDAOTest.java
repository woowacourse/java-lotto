package lotto.dao;

import lotto.dto.LottoHistoryDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static lotto.dao.DBUtil.getConnection;
import static org.assertj.core.api.Assertions.assertThat;

class LottoHistoryDAOTest {
    LottoHistoryDAO lottoHistoryDAO;
    Connection connection;

    @BeforeEach
    void setUp() throws Exception {
        connection = getConnection();
        connection.setAutoCommit(false);
        lottoHistoryDAO = new LottoHistoryDAO(connection);
    }

    //존재하는 라운드
    @Test
    public void selectTest1() throws Exception {
        LottoHistoryDTO lottoHistoryDTO = lottoHistoryDAO.selectLottoHistory(1);

        assertThat(lottoHistoryDTO.getLottoResultDTO()).isNotNull();
        assertThat(lottoHistoryDTO.getWinningLottoDTO()).isNotNull();
    }

    //존재하지 않는 라운드
    @Test
    public void selectTest2() throws SQLException {
        LottoHistoryDTO lottoHistoryDTO = lottoHistoryDAO.selectLottoHistory(200);

        assertThat(lottoHistoryDTO.getLottoResultDTO()).isNull();
        assertThat(lottoHistoryDTO.getWinningLottoDTO()).isNull();
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback();
        connection.close();
    }
}