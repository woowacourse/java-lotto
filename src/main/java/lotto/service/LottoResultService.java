package lotto.service;

import lotto.dao.DBUtil;
import lotto.dao.LottoResultDAO;
import lotto.dao.WinningLottoDAO;
import lotto.domain.lotto.LottoTicketGroup;
import lotto.domain.lottoresult.LottoResult;
import lotto.domain.lottoresult.WinningLotto;
import lotto.dto.LottoResultDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class LottoResultService {
    private static LottoResultService instance;

    private LottoResultService() {
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
        Connection connection = DBUtil.getConnection();
        WinningLottoDAO.getInstance(connection).insertWinningLotto(round, winningLotto);
        LottoResultDAO.getInstance(connection).insertResult(round, lottoResultDTO);
    }
}
