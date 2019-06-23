package lotto.persistence;

import lotto.domain.WinningLotto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoDAOTest {
    private Connection con;
    private WinningLottoDAO winningLottoDao;
    private RoundDAO roundDao;
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        this.con = Connector.getConnection();
        this.winningLottoDao = new WinningLottoDAO(con);
        this.roundDao = new RoundDAO(con);
        this.winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
    }

    @Test
    void addWinningLottoTest() throws SQLException {
        roundDao.addRound(100, 0.1);
        int roundId = roundDao.getLatestRoundId();
        winningLottoDao.addWinningLotto(roundId, winningLotto);
        assertThat(winningLottoDao.findWinningLottoByRoundId(roundId)).isEqualTo(winningLotto);
        roundDao.removeRoundById(roundId);
    }

    @AfterEach
    void tearDown() {
        Connector.closeConnection(con);
    }
}
