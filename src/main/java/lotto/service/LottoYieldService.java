package lotto.service;

import lotto.dao.LottoRoundDAO;
import lotto.dao.LottoYieldDAO;
import lotto.domain.result.LottoResult;

import java.math.BigDecimal;
import java.sql.SQLException;

public class LottoYieldService {
    private LottoRoundDAO lottoRoundDAO;
    private LottoYieldDAO lottoYieldDAO;

    public LottoYieldService(LottoRoundDAO lottoRoundDAO, LottoYieldDAO lottoYieldDAO) {
        this.lottoRoundDAO = lottoRoundDAO;
        this.lottoYieldDAO = lottoYieldDAO;
    }

    public void saveLottoYield(LottoResult lottoResult) throws SQLException {
        lottoYieldDAO.saveYield(lottoResult.yield(), lottoRoundDAO.totalRound());
    }

    public BigDecimal inquireLottoYield() throws SQLException {
        return lottoYieldDAO.inquireYield(lottoRoundDAO.totalRound());
    }

    public BigDecimal inquireLottoYield(int round) throws SQLException {
        return lottoYieldDAO.inquireYield(round);
    }
}
