package lotto.dao;

import lotto.db.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class RoundDaoTest {
    private Connection conn;
    private RoundDao roundDao;

    @BeforeEach
    void setUp() {
        conn = new DatabaseConnection().getConnection();
        roundDao = new RoundDao(conn);
    }

    @Test
    void 라운드_추가() throws Exception {
        int presentRound = 10;
        int price = 5000;
        roundDao.addRound(presentRound, price);
    }

    @Test
    void 첫_라운드_조회() throws Exception {
        assertThat(roundDao.findLatestRound()).isEqualTo(0);
    }

    @Test
    void 마지막_라운드_조회() throws Exception {
        roundDao.addRound(1,3000);
        assertThat(roundDao.findLatestRound()).isEqualTo(1);
    }

    @Test
    void 전체_라운드_조회() throws Exception {
        roundDao.addRound(4, 1000);
        roundDao.addRound(5, 2000);
        roundDao.addRound(6, 3000);
        assertThat(roundDao.findAllRound()).isEqualTo(Arrays.asList(4, 5, 6));
    }

    @Test
    void 가격_조회() throws SQLException {
        roundDao.addRound(1, 5000);
        roundDao.addRound(2, 9000);

        assertThat(roundDao.findPriceByRound(1)).isEqualTo(5000);
    }

    @AfterEach
    void tearDown() throws SQLException {
        roundDao.deleteAllRound();
    }
}
