package lotto.domain.dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class RoundDAOTest {
    @Test
    void 회차_등록하기() throws SQLException {
        RoundDAO.registerCount();
    }

    @Test
    void 마지막_회차_조회하기() throws SQLException {
        assertThat(RoundDAO.searchMaxCount()).isEqualTo(4);
    }
}
