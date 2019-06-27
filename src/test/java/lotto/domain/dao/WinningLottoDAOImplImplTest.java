package lotto.domain.dao;

import lotto.domain.dto.WinningLottoDTO;
import lotto.domain.model.Lotto;
import lotto.domain.model.NumberSet;
import lotto.domain.model.WinningLotto;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoDAOImplImplTest {
    private WinningLottoDAOImpl winningLottoDAOImpl;
    private WinningLottoDTO winningLottoDTO;

    @Before
    public void setUp() {
        winningLottoDAOImpl = WinningLottoDAO.getInstance();
        winningLottoDTO = new WinningLottoDTO();
        winningLottoDTO.setRound(0);
        winningLottoDTO.setWinningLotto(new Lotto(Arrays.asList(
                NumberSet.of(1),
                NumberSet.of(2),
                NumberSet.of(3),
                NumberSet.of(4),
                NumberSet.of(5),
                NumberSet.of(6))));
        winningLottoDTO.setBonusNum(NumberSet.of(7));
    }

    @Test
    public void addWinningLotto_테스트() {
        winningLottoDAOImpl.addWinningLotto(winningLottoDTO);
        winningLottoDAOImpl.deleteWinningLotto(0);
    }

    @Test
    public void getNewRound_테스트() {
        assertThat(winningLottoDAOImpl.getLatestRound()).isNotNull();
    }

    @Test
    public void getWinningLotto_테스트() {
        winningLottoDAOImpl.addWinningLotto(winningLottoDTO);
        WinningLotto winningLotto = winningLottoDAOImpl.getWinningLotto(0);
        assertThat(winningLotto).isNotNull();
        winningLottoDAOImpl.deleteWinningLotto(0);
    }
}
