package lotto.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class RoundDAOTest {
    private RoundDAO roundDAO;
    private String lottoRound;

    @BeforeEach
    void setup_db() throws SQLException {
        roundDAO = new RoundDAO(DBManager.getConnection());
        lottoRound = roundDAO.getCurrentRound().toString();
    }

    @Test
    void test1_로또_회차_추가() throws SQLException {
        lottoRound = roundDAO.getNextRound().toString();
        roundDAO.addRound(lottoRound);
    }

    @Test
    void test2_로또_회차_삭제() throws SQLException {
        roundDAO.deleteRound(lottoRound);
    }
}
