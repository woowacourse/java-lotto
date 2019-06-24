package lotto.domain.dao;

import lotto.domain.dto.LottoDTO;
import lotto.domain.dto.WinningLottoDTO;
import lotto.domain.model.NumberSet;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoDAOTest {
    private LottoDAO lottoDAO;
    private LottoDTO lottoDTO;
    private WinningLottoDAO winningLottoDAO;
    private WinningLottoDTO winningLottoDTO;

    @Before
    public void setUp() {
        lottoDAO = new LottoDAO();
        lottoDTO = new LottoDTO();
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

        lottoDTO.setRound(0);
        lottoDTO.setFirstNum(NumberSet.of(1));
        lottoDTO.setSecondNum(NumberSet.of(2));
        lottoDTO.setThirdNum(NumberSet.of(3));
        lottoDTO.setForthNum(NumberSet.of(4));
        lottoDTO.setFifthNum(NumberSet.of(5));
        lottoDTO.setSixthNum(NumberSet.of(6));
    }

    @Test
    public void addLotto_테스트() {
        // lotto의 pk인 round가 winningLotto의 pk인 round와 fk로 연결되어 있어 winninglotto를 먼저 add함
        winningLottoDAO.addWinningLotto(winningLottoDTO);
        lottoDAO.addLotto(lottoDTO);

        // delete test data
        lottoDAO.deleteLotto(0);
        winningLottoDAO.deleteWinningLotto(0);
    }

    @Test
    public void getPurchasedLotto_테스트() {
        assertThat(lottoDAO.getPurchasedLotto(1)).isNotNull();
    }
}
