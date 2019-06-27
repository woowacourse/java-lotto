package lotto.domain.dao;

import lotto.domain.dto.LottoDTO;
import lotto.domain.dto.WinningLottoDTO;
import lotto.domain.model.Lotto;
import lotto.domain.model.NumberSet;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoDAOImplTest {
    private LottoDAOImpl lottoDAOImpl;
    private LottoDTO lottoDTO;
    private WinningLottoDAOImpl winningLottoDAOImpl;
    private WinningLottoDTO winningLottoDTO;

    @Before
    public void setUp() {
        lottoDAOImpl = LottoDAO.getInstance();
        lottoDTO = new LottoDTO();
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

        lottoDTO.setRound(0);
        lottoDTO.setLotto(new Lotto(Arrays.asList(
                NumberSet.of(1),
                NumberSet.of(2),
                NumberSet.of(3),
                NumberSet.of(4),
                NumberSet.of(5),
                NumberSet.of(6))));
    }

    @Test
    public void addLotto_테스트() {
        // lotto의 pk인 round가 winningLotto의 pk인 round와 fk로 연결되어 있어 winninglotto를 먼저 add함
        winningLottoDAOImpl.addWinningLotto(winningLottoDTO);
        lottoDAOImpl.addLotto(lottoDTO);

        // delete test data
        lottoDAOImpl.deleteLotto(0);
        winningLottoDAOImpl.deleteWinningLotto(0);
    }

    @Test
    public void getPurchasedLotto_테스트() {
        assertThat(lottoDAOImpl.getPurchasedLotto(1)).isNotNull();
    }
}
