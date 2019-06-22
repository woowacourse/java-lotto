package lotto.dao;

import lotto.db.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class RoundDaoTest {
    private Connection conn;
    private RoundDao roundDao;

    @BeforeEach
    void setUp() throws SQLException{
        conn = new DatabaseConnection().getConnection();
        roundDao = new RoundDao(conn);
        conn.setAutoCommit(false);
    }

    @Test
    void 라운드_추가() throws Exception {
        int presentRound = 10;
        int price = 5000;
        roundDao.addRound(presentRound, price);
    }


    @Test
    void 마지막_라운드_조회() throws Exception {
        roundDao.addRound(100,3000);
        assertThat(roundDao.findLatestRound()).isEqualTo(100);
    }


    @Test
    void 가격_조회() throws SQLException {
        roundDao.addRound(100, 5000);
        roundDao.addRound(101, 9000);

        assertThat(roundDao.findPriceByRound(100)).isEqualTo(5000);
    }

    @AfterEach
    void tearDown() throws SQLException {
        conn.rollback();
    }
}
