package lotto.domain.dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

public class WinningDAOTest {
    @Test
    void 당첨번호_저장하기() throws SQLException {
        WinningDAO.addWinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
    }
}
