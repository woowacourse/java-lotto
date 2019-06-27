package lotto.service;

import lotto.dao.DBConnection;
import lotto.dao.JdbcTemplate;
import lotto.dao.LottoResultDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.lotto.LottoTicketGroup;
import lotto.domain.lottoresult.LottoResult;
import lotto.domain.lottoresult.WinningLotto;
import lotto.dto.LottoResultDTO;

import java.sql.SQLException;

public class LottoResultService {
    private static LottoResultService instance;

    private final WinningLottoDao winningLottoDAO;
    private final LottoResultDao lottoResultDAO;

    private LottoResultService() {
        JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance(DBConnection.getConnection());
        winningLottoDAO = WinningLottoDao.getInstance(jdbcTemplate);
        lottoResultDAO = LottoResultDao.getInstance(jdbcTemplate);
    }

    public static LottoResultService getInstance() {
        if (instance == null) {
            instance = new LottoResultService();
        }
        return instance;
    }

    public LottoResultDTO createResult(int round, String winningNums, String bonusNum, LottoTicketGroup lottos) throws SQLException {
        WinningLotto winningLotto = WinningLotto.create(winningNums, bonusNum);
        LottoResult lottoResult = lottos.match(winningLotto);

        LottoResultDTO lottoResultDTO = new LottoResultDTO();
        lottoResultDTO.setWinningReward(lottoResult.getRewards());
        lottoResultDTO.setRankCounts(lottoResult.getRankCounts());
        lottoResultDTO.setEarningRate(lottoResult.getEarningRate());

        saveResult(round, winningLotto, lottoResultDTO);

        return lottoResultDTO;
    }

    private void saveResult(int round, WinningLotto winningLotto, LottoResultDTO lottoResultDTO) throws SQLException {
        winningLottoDAO.insertWinningLotto(round, winningLotto);
        lottoResultDAO.insertResult(round, lottoResultDTO);
    }
}
