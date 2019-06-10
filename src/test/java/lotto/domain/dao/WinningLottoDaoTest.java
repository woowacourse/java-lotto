package lotto.domain.dao;

import lotto.DatabaseConnection;
import lotto.domain.LottoTicket;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoDaoTest {
    Connection connection = new DatabaseConnection().getConnection();
    WinningLottoDao winningLottoDao = new WinningLottoDao(connection);

    @Test
    void 우승_로또_추가() throws SQLException {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusBall = 7;
        WinningLotto winningLotto = new WinningLotto(lottoTicket, bonusBall);
        winningLottoDao.addWinningLotto(1, winningLotto);
    }

    @Test
    void 우승_로또_검색() throws SQLException {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusBall = 7;
        WinningLotto winningLotto = new WinningLotto(lottoTicket, bonusBall);
        assertThat(winningLottoDao.findWinningLottoByRound(1)).isEqualTo(winningLotto);
    }
}