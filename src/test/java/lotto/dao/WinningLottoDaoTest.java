package lotto.dao;

import lotto.db.DatabaseConnection;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoDaoTest {
    private Connection conn;
    private WinningLottoDao winningLottoDao;
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() throws SQLException{
        conn = new DatabaseConnection().getConnection();
        conn.setAutoCommit(false);
        winningLottoDao = new WinningLottoDao(conn);
        Lotto winningNumbers = new Lotto(Arrays.asList(1,2,3,10,11,12));
        LottoNumber lottoNumber = LottoNumber.valueOf(19);
        winningLotto = new WinningLotto(winningNumbers,lottoNumber);
    }

    @Test
    void addWinningLotto() throws SQLException {
        winningLottoDao.addWinningLotto(1,winningLotto);
    }

    @Test
    void findWinningLottoByRound() throws SQLException{
        winningLottoDao.addWinningLotto(1,winningLotto);
        assertThat(winningLottoDao.findWinningLottoByRound(1)).isEqualTo(winningLotto);
    }

    @AfterEach
    void tearDown() throws SQLException{
        conn.rollback();
    }
}