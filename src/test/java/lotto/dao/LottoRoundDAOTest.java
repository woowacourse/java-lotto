package lotto.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static lotto.dao.DBUtil.getConnection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoRoundDAOTest {
    LottoRoundDAO lottoRoundDAO;
    Connection connection;

    @BeforeEach
    void setUp() throws Exception {
        connection = getConnection();
        connection.setAutoCommit(false);
        lottoRoundDAO = LottoRoundDAO.getInstance(connection);
    }

    //에러 발생1 (해당 라운드 이미 존재)
    @Test
    public void insertTest1() {
        assertThrows(SQLException.class, () ->
                lottoRoundDAO.insertRound(1)
        );
    }


    //정상 실행
    @Test
    public void insertTest2() {
        assertDoesNotThrow(() ->
                lottoRoundDAO.insertRound(200)
        );
    }

    @Test
    public void selectRoundsTest() throws Exception {
        lottoRoundDAO.insertRound(200);
        lottoRoundDAO.insertRound(201);
        lottoRoundDAO.insertRound(202);

        assertThat(lottoRoundDAO.selectLottoRounds().size()).isGreaterThan(3);
    }

    @Test
    public void selectMaxRoundTest() throws SQLException {
        lottoRoundDAO.insertRound(202);

        assertThat(lottoRoundDAO.selectMaxRound()).isEqualTo(202);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback();
        connection.close();
    }
}