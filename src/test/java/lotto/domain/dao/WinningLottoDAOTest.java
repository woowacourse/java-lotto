package lotto.domain.dao;

import lotto.domain.dto.WinningLottoDTO;
import lotto.domain.model.NumberSet;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoDAOTest {
    private WinningLottoDAO winningLottoDAO;
    private WinningLottoDTO winningLottoDTO;

    @Before
    public void setUp() {
        winningLottoDAO = new WinningLottoDAO();
        winningLottoDTO = new WinningLottoDTO();
        winningLottoDTO.setRound(0);
        winningLottoDTO.setFirstNum(NumberSet.of(1));
        winningLottoDTO.setSecondNum(NumberSet.of(2));
        winningLottoDTO.setThirdNum(NumberSet.of(3));
        winningLottoDTO.setForthNum(NumberSet.of(4));
        winningLottoDTO.setFifthNum(NumberSet.of(5));
        winningLottoDTO.setSixthNum(NumberSet.of(6));
        winningLottoDTO.setBonusNum(NumberSet.of(7));
    }

    @Test
    public void addWinningLotto_테스트() {
        winningLottoDAO.addWinningLotto(winningLottoDTO);
        winningLottoDAO.deleteWinningLotto(0);
    }

    @Test
    public void getNewRound_테스트() {
        assertThat(winningLottoDAO.getLatestRound()).isNotNull();
    }

    @Test
    public void getWinningLotto_테스트() {
        winningLottoDAO.addWinningLotto(winningLottoDTO);
        WinningLottoDTO winningLotto = winningLottoDAO.getWinningLotto(0);
        assertThat(winningLotto.getRound()).isEqualTo(0);
        winningLottoDAO.deleteWinningLotto(0);
    }
}
