package lotto.domain.dao;

import lotto.domain.dto.WinningLottoDTO;
import lotto.domain.model.NumberSet;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoDAOTest {
    private WinningLottoDAO winningLottoDAO;

    @Before
    public void setUp() {
        winningLottoDAO = new WinningLottoDAO();
    }

    @Test
    public void addWinningLotto() {
        try {
            WinningLottoDTO winningLottoDTO = new WinningLottoDTO();
            winningLottoDTO.setRound(0);
            winningLottoDTO.setFirstNum(NumberSet.of(1));
            winningLottoDTO.setSecondNum(NumberSet.of(2));
            winningLottoDTO.setThirdNum(NumberSet.of(3));
            winningLottoDTO.setForthNum(NumberSet.of(4));
            winningLottoDTO.setFifthNum(NumberSet.of(5));
            winningLottoDTO.setSixthNum(NumberSet.of(6));
            winningLottoDTO.setBonusNum(NumberSet.of(7));
            winningLottoDAO.addWinningLotto(winningLottoDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getLatestRoundFromWinningLotto() throws SQLException {
        int round = winningLottoDAO.getNewRound();
        assertThat(round).isEqualTo(2);
    }
}
