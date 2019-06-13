package lotto.domain.dao;

import lotto.domain.lottoTicket.Lotto;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningDAOTest {
    @Test
    void 당첨번호_저장하기() throws SQLException {
        WinningDAO.addWinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
    }

    @Test
    void 당첨_번호_조회하기() throws SQLException {
        Lotto searchWinningLotto = new Lotto(Arrays.asList(12, 2, 3, 4, 5, 6));
        assertThat(WinningDAO.searchWinningNumbers(1)).isEqualTo(searchWinningLotto);
    }

    @Test
    void 당첨_보너스_번호_조회하기() throws SQLException {
        assertThat(WinningDAO.searchWinningBonus(1)).isEqualTo(20);
    }
}
