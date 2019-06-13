package lotto.domain.dao;

import lotto.domain.model.Lotto;
import lotto.domain.model.Number;
import lotto.domain.model.NumberSet;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoDAOTest {
    private WinningLottoDAO winningLottoDAO;

    @Before
    public void setUp() {
        winningLottoDAO = new WinningLottoDAO();
    }

    @Test
    public void addWinningLotto() {
        List<Number> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(NumberSet.of(i));
        }
        Lotto lotto = new Lotto(lottoNumbers);
        Number bonusNumber = NumberSet.of(7);
        int round = 2;

        try {
            winningLottoDAO.addWinningLotto(lotto, bonusNumber, round);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getLatestRoundFromWinningLotto() throws SQLException {
        int round = winningLottoDAO.getLatestRound();
        assertThat(round).isEqualTo(2);
    }
}
