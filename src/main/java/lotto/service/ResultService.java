package lotto.service;

import lotto.dao.LottoDao;
import lotto.dao.LottoGameDao;
import lotto.dao.ResultDao;
import lotto.dao.WinningNumberDao;
import lotto.dto.LottoDto;
import lotto.dto.LottoGameDto;
import lotto.dto.ResultDto;
import lotto.dto.WinningNumberDto;

import java.sql.SQLException;
import java.util.List;

public class ResultService {

    private LottoGameDao lottoGameDao;
    private LottoDao lottoDao;
    private WinningNumberDao winningNumberDao;
    private ResultDao resultDao;

    public ResultService() {
        lottoGameDao = LottoGameDao.getInstance();
        lottoDao = LottoDao.getInstance();
        resultDao = ResultDao.getInstance();
        winningNumberDao = WinningNumberDao.getInstance();
    }

    public LottoGameDto findGameDataByRound(int round) throws SQLException {
        return lottoGameDao.findRoundById(round);
    }

    public List<LottoDto> findLottosByRound(int round) throws SQLException {
        return lottoDao.findAllBoughtLottoByRound(round);
    }

    public WinningNumberDto findWinningNumberByRound(int round) throws SQLException {
        return winningNumberDao.findWinningLottoByRound(round);
    }

    public ResultDto findResultByRound(int round) throws SQLException {
        return resultDao.findResultByRound(round);
    }
}
