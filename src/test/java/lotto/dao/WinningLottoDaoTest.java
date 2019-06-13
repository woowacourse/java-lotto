package lotto.dao;

import lotto.DatabaseConnection;
import lotto.domain.LottoTicket;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoDaoTest {
    Connection connection;
    WinningLottoDao winningLottoDao;

    @BeforeEach
    void setUp() {
        connection = new DatabaseConnection().getConnection();
        winningLottoDao = new WinningLottoDao(connection);
    }

    @Test
    void 우승_로또_추가() throws SQLException {
        connection.setAutoCommit(false);
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusBall = 7;
        int round = 100;
        WinningLotto winningLotto = new WinningLotto(lottoTicket, bonusBall);
        winningLottoDao.addWinningLotto(round, winningLotto);
        connection.rollback();
    }

    @Test
    void 우승_로또_확인() throws SQLException {
        connection.setAutoCommit(false);
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusBall = 7;
        int round = 100;

        WinningLotto winningLotto = new WinningLotto(lottoTicket, bonusBall);
        winningLottoDao.addWinningLotto(round, winningLotto);

        assertThat(winningLottoDao.findWinningLottoByRound(round)).isEqualTo(winningLotto);
        connection.rollback();
    }

    @Test
    void 우승_로또_삭제() throws SQLException {
        connection.setAutoCommit(false);
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusBall = 7;
        int round = 100;
        WinningLotto winningLotto = new WinningLotto(lottoTicket, bonusBall);

        winningLottoDao.addWinningLotto(round, winningLotto);
        winningLottoDao.deleteWinningLottoByRound(round);

        assertThat(winningLottoDao.findWinningLottoByRound(round)).isNull();
        connection.rollback();
    }
}