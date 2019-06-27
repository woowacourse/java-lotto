package lotto.service;

import lotto.dao.LottoResultDAO;
import lotto.dao.LottoRoundDAO;
import lotto.domain.result.LottoRank;
import lotto.domain.result.LottoResult;

import java.sql.SQLException;
import java.util.Map;

public class LottoResultService {
    private LottoRoundDAO lottoRoundDAO;
    private LottoResultDAO lottoResultDAO;

    public LottoResultService(LottoRoundDAO lottoRoundDAO, LottoResultDAO lottoResultDAO) {
        this.lottoRoundDAO = lottoRoundDAO;
        this.lottoResultDAO = lottoResultDAO;
    }

    public void saveLottoResult(LottoResult lottoResult) throws SQLException {
        lottoResultDAO.saveLottoResult(lottoResult, lottoRoundDAO.totalRound());
    }

    public Map<LottoRank, Integer> inquireLottoResult() throws SQLException {
        return lottoResultDAO.inquireLottoResult(lottoRoundDAO.totalRound());
    }

    public Map<LottoRank, Integer> inquireLottoResult(int round) throws SQLException {
        return lottoResultDAO.inquireLottoResult(round);
    }
}
