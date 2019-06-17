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
    public static LottoResultDTO createResult(int round, String winningNums, String bonusNum, LottoTicketGroup lottos) throws SQLException {
        WinningLotto winningLotto = WinningLotto.create(winningNums, bonusNum);
        LottoResult lottoResult = lottos.match(winningLotto);

        LottoResultDTO lottoResultDTO = new LottoResultDTO();
        lottoResultDTO.setWinningReward(lottoResult.getRewards());
        lottoResultDTO.setRankCounts(lottoResult.getRankCounts());
        lottoResultDTO.setEarningRate(lottoResult.getEarningRate());

        saveResult(round, winningLotto, lottoResultDTO);

        return lottoResultDTO;
    }

    private static void saveResult(int round, WinningLotto winningLotto, LottoResultDTO lottoResultDTO) throws SQLException {
        Connection connection = DBUtil.getConnection();
        new WinningLottoDAO(connection).insertWinningLotto(round, winningLotto);
        new LottoResultDAO(connection).insertResult(round, lottoResultDTO);
    }
}
