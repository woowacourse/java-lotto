package lotto.domain.dao;

import lotto.domain.dto.ResultDTO;
import lotto.domain.dto.WinningLottoDTO;
import lotto.domain.model.Lotto;
import lotto.domain.model.NumberSet;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultDAOImplTest {
    private ResultDAOImpl resultDAOImpl;
    private ResultDTO resultDTO;
    private WinningLottoDAOImpl winningLottoDAOImpl;
    private WinningLottoDTO winningLottoDTO;

    @Before
    public void setUp() {
        resultDAOImpl = ResultDAO.getInstance();
        resultDTO = new ResultDTO();
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

        resultDTO.setRound(0);
        resultDTO.setMoney(5000);
        resultDTO.setFirstPrize(1);
        resultDTO.setSecondPrize(1);
        resultDTO.setThirdPrize(1);
        resultDTO.setForthPrize(1);
        resultDTO.setFifthPrize(1);
        resultDTO.setProfitRate(20.0);
    }

    @Test
    public void addPrizeResult() {
        winningLottoDAOImpl.addWinningLotto(winningLottoDTO);
        resultDAOImpl.setResult(resultDTO);
        resultDAOImpl.deleteResult(0);
        winningLottoDAOImpl.deleteWinningLotto(0);
    }

    @Test
    public void getResult_테스트() {
        winningLottoDAOImpl.addWinningLotto(winningLottoDTO);
        resultDAOImpl.setResult(resultDTO);
        ResultDTO actual = resultDAOImpl.getResult(0);
        assertThat(actual).isNotNull();
        resultDAOImpl.deleteResult(0);
        winningLottoDAOImpl.deleteWinningLotto(0);
    }
}
