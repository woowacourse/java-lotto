package lotto.service;

import lotto.ConnectionFactory;
import lotto.dao.LottoDao;
import lotto.dao.ResultDao;
import lotto.dao.WinningLottoDao;
import lotto.dto.LottoDto;
import lotto.dto.ResultDto;
import lotto.dto.WinningLottoDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ResultService {
    private static WinningLottoDao winningLottoDao;
    private static LottoDao lottoDao;
    private static ResultDao resultDao;

    static {
        Connection con = ConnectionFactory.connect();
        winningLottoDao = new WinningLottoDao(con);
        lottoDao = new LottoDao(con);
        resultDao = new ResultDao(con);
    }

    public List<LottoDto> getPurchasedLotto(int round) throws SQLException {
        return lottoDao.getLottos(round);
    }

    public WinningLottoDto getWinningLotto(int round) throws SQLException {
        return winningLottoDao.getWinningLotto(round);
    }

    public int getMaxRound() throws SQLException {
        return resultDao.findRoundNo();
    }

    public ResultDto getResult(int round) throws SQLException {
        return resultDao.getResult(round);
    }
}