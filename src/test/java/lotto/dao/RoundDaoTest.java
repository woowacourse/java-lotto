package lotto.dao;

import lotto.db.DatabaseConnection;
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
    void setUp() throws SQLException{
        conn = new DatabaseConnection().getConnection();
        roundDao = new RoundDao(conn);
        roundDao.deleteAllRound();
    }

    @Test
    void 라운드_추가() throws Exception{
        int presentRound = roundDao.findLatestRound() + 1;
        roundDao.addRound(presentRound);
    }

    @Test
    void 마지막_라운드_조회() throws Exception{
        assertThat(roundDao.findLatestRound()).isEqualTo(1);
    }

    @Test
    void 전체_라운드_조회() throws Exception{
        roundDao.addRound(4);
        roundDao.addRound(5);
        roundDao.addRound(6);
        assertThat(roundDao.findAllRound()).isEqualTo(Arrays.asList(4,5,6));
    }
}
