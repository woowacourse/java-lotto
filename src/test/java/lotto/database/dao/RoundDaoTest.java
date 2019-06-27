package lotto.database.dao;

import lotto.database.JdbcConnector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RoundDAOTest {
    private Connection con;
    private RoundDAO roundDao;

    @BeforeEach
    public void setUp() throws Exception {
        this.con = JdbcConnector.getConnection();
        this.roundDao = new RoundDAO(con);
        con.setAutoCommit(false);

    }

    @Test
    public void 라운드_추가() throws SQLException {
        roundDao.addRound(1);
        assertThat(roundDao.getMaxRound()).isEqualTo(1);
        roundDao.deleteRound(1);
    }

    @Test
    public void 라운드_없을때_0() throws SQLException {
        roundDao.deleteRoundAll();
        assertThat(roundDao.getMaxRound()).isEqualTo(0);
    }

    @Test
    public void 맥스_라운드() throws SQLException {
        roundDao.addRound(5);
        assertThat(roundDao.getMaxRound()).isEqualTo(5);
    }

    @AfterEach
    void tearDown() throws SQLException {
        con.rollback();
        con.close();
    }
}