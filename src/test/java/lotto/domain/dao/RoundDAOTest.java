package lotto.domain.dao;

import lotto.db.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

public class RoundDAOTest {
    Connection connection = new DatabaseConnection().getConnection();
    RoundDAO roundDAO = new RoundDAO(connection);

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
