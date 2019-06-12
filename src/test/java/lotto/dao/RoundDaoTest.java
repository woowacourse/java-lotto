package lotto.dao;

import lotto.db.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

public class RoundDaoTest {
    private Connection conn;
    private RoundDao roundDAO;

    @BeforeEach
    void setUp(){
        conn = new DatabaseConnection().getConnection();
        roundDAO = new RoundDao(conn);
    }
    @Test
    void 라운드_추가() throws Exception{
        int round = 2;
        roundDAO.addRound(round);
    }

    @Test
    void 마지막_라운드_조회() throws Exception{
        assertThat(roundDAO.findLatestRound()).isEqualTo(0);
    }
}
