package lotto.domain.dao;

import lotto.domain.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoDaoTest {
    private WinningLottoDao winningLottoDAO;

    @BeforeEach
    void setUp() {
        winningLottoDAO = new WinningLottoDao();
    }

    @Test
    void add() throws SQLException {
        int round = 6;
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6", 7);
        winningLottoDAO.addWinningLotto(round, winningLotto);
    }

    @Test
    void searchWinningLotto() throws SQLException {
        int round = 6;
        List<Integer> winningLotto = Arrays.asList(1,2,3,4,5,6);
        assertThat(winningLottoDAO.findWinningLottoByRound(round)).isEqualTo(winningLotto);
    }

    @Test
    void searchBonusNumber() throws SQLException {
        int round = 6;
        int bonusNumber = 7;
        assertThat(winningLottoDAO.findBonusNumByRound(round)).isEqualTo(bonusNumber);
    }
}