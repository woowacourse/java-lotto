package lotto.dao;

import lotto.db.DatabaseConnection;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoDAOTest {
    private Connection conn;
    private WinningLottoDAO winningLottoDAO;
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        conn = new DatabaseConnection().getConnection();
        winningLottoDAO = new WinningLottoDAO(conn);
        Lotto winningNumbers = new Lotto(Arrays.asList(1,2,3,10,11,12));
        LottoNumber lottoNumber = LottoNumber.valueOf(19);
        winningLotto = new WinningLotto(winningNumbers,lottoNumber);
    }

    @Test
    void addWinningLotto() throws SQLException {
        winningLottoDAO.addWinningLotto(1,winningLotto);
    }

    @Test
    void findWinningLottoByRound() throws SQLException{
        assertThat(winningLottoDAO.findWinningLottoByRound(1)).isEqualTo(winningLotto);
    }
}