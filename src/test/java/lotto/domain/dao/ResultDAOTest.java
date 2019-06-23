package lotto.domain.dao;

import lotto.domain.dto.ResultDTO;
import lotto.domain.dto.WinningLottoDTO;
import lotto.domain.model.NumberSet;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultDAOTest {
    private ResultDAO resultDAO;
    private ResultDTO resultDTO;
    private WinningLottoDAO  winningLottoDAO;
    private WinningLottoDTO winningLottoDTO;

    @Before
    public void setUp() {
        resultDAO = new ResultDAO();
        resultDTO = new ResultDTO();
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
    public void addPrizeResult() throws SQLException {
        winningLottoDAO.addWinningLotto(winningLottoDTO);
        resultDAO.setResult(resultDTO);
        resultDAO.deleteResult(0);
        winningLottoDAO.deleteWinningLotto(0);
    }

    @Test
    public void getResult_테스트() throws SQLException {
        winningLottoDAO.addWinningLotto(winningLottoDTO);
        resultDAO.setResult(resultDTO);
        ResultDTO actual = resultDAO.getResult(0);
        assertThat(actual).isNotNull();
        resultDAO.deleteResult(0);
        winningLottoDAO.deleteWinningLotto(0);
    }
}
