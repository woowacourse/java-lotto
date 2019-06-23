package lotto.domain.service;

import lotto.domain.dao.LottoDAO;
import lotto.domain.dao.ResultDAO;
import lotto.domain.dao.WinningLottoDAO;
import lotto.domain.dto.LottoDTO;
import lotto.domain.dto.ResultDTO;
import lotto.domain.dto.WinningLottoDTO;
import lotto.domain.model.Lotto;
import lotto.domain.model.PurchasedLotto;
import lotto.domain.model.Rank;
import lotto.domain.model.WinningLotto;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ResultService {
    public void setResult(final int round, final int money) throws SQLException {
        ResultDTO resultDTO = new ResultDTO();
        ResultDAO resultDAO = new ResultDAO();

        WinningLotto winningLotto = getWinningLotto(round);
        PurchasedLotto purchasedLotto = getPurchasedLotto(round);

        List<Rank> ranks = winningLotto.match(purchasedLotto);
        Map<Rank, Integer> prizeResult = winningLotto.calculatePrize(ranks);

        resultDTO.setFirstPrize(prizeResult.get(Rank.FIRST) / Rank.FIRST.getPrize());
        resultDTO.setSecondPrize(prizeResult.get(Rank.SECOND) / Rank.SECOND.getPrize());
        resultDTO.setThirdPrize(prizeResult.get(Rank.THIRD) / Rank.THIRD.getPrize());
        resultDTO.setForthPrize(prizeResult.get(Rank.FORTH) / Rank.FORTH.getPrize());
        resultDTO.setFifthPrize(prizeResult.get(Rank.FIFTH) / Rank.FIFTH.getPrize());
        resultDTO.setRound(round);
        resultDTO.setMoney(money);
        resultDTO.setProfitRate(calculateProfitRate(money, prizeResult));

        resultDAO.setResult(resultDTO);
    }

    private double calculateProfitRate(final double money, final Map<Rank, Integer> prizeResult) {
        int prizeSum = 0;
        for (Rank rank : Rank.ranks()) {
            prizeSum += prizeResult.get(rank);
        }
        return Math.round((prizeSum / money) * 1000) / 1000.0;
    }

    private PurchasedLotto getPurchasedLotto(final int round) throws SQLException {
        LottoDAO lottoDAO = new LottoDAO();
        PurchasedLotto purchasedLotto = new PurchasedLotto();
        List<LottoDTO> purchasedLottoDTOs = lottoDAO.getPurchasedLotto(round);

        for (LottoDTO lottoDTO : purchasedLottoDTOs) {
             purchasedLotto.addLotto(new Lotto(Arrays.asList(lottoDTO.getFifthNum(),
                     lottoDTO.getSecondNum(),
                     lottoDTO.getThirdNum(),
                     lottoDTO.getForthNum(),
                     lottoDTO.getFifthNum(),
                     lottoDTO.getSixthNum())));
        }

        return purchasedLotto;
    }

    private WinningLotto getWinningLotto(int round) throws SQLException {
        WinningLottoDAO winningLottoDAO = new WinningLottoDAO();
        WinningLottoDTO winningLottoDTO = winningLottoDAO.getWinningLotto(round);

        Lotto winningLottoNumbers = new Lotto(Arrays.asList(winningLottoDTO.getFirstNum(),
                winningLottoDTO.getSecondNum(),
                winningLottoDTO.getThirdNum(),
                winningLottoDTO.getForthNum(),
                winningLottoDTO.getFifthNum(),
                winningLottoDTO.getSixthNum()));

        return new WinningLotto(winningLottoNumbers, winningLottoDTO.getBonusNum());
    }

    public ResultDTO getResult(final int newRound) throws SQLException {
        ResultDAO resultDAO = new ResultDAO();
        return resultDAO.getResult(newRound);
    }
}
