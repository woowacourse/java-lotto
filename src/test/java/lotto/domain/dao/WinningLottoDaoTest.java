package lotto.domain.dao;

import lotto.domain.WinningLotto;
import lotto.domain.util.DBUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoDaoTest {
    private WinningLottoDao winningLottoDAO;
    private DataSource dataSource = DBUtil.getDataSource();


    @BeforeEach
    void setUp() {
        winningLottoDAO = new WinningLottoDao(dataSource);
    }

    @Test
    void add() throws SQLException {
        int round = 0;
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6", 7);
        winningLottoDAO.addWinningLotto(round, winningLotto);
    }

    @Test
    void searchWinningLotto() throws SQLException {
        int round = 0;
        List<Integer> winningLotto = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(winningLottoDAO.findWinningLottoByRound(round)).isEqualTo(winningLotto);
    }

    @Test
    void searchBonusNumber() throws SQLException {
        int round = 0;
        int bonusNumber = 7;
        assertThat(winningLottoDAO.findBonusNumByRound(round)).isEqualTo(bonusNumber);
    }
}