package lotto.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class RoundDAOTest {
    private RoundDAO roundDAO;

    @BeforeEach
    void setup_db() {
        roundDAO = new RoundDAO(DBManager.getConnection());
    }

    @Test
    void test1_최신_로또_회차_받기() throws SQLException {
        assertThat(1).isEqualTo(roundDAO.getNextRound());
    }

    @Test
    void test2_로또_회차_추가() throws SQLException {
        roundDAO.addRound("1");
    }

    @Test
    void test3_로또_회차_삭제() throws SQLException {
        roundDAO.deleteRound("1");
    }
}
