package lotto.domain.service;

import lotto.domain.dao.LottoDAO;
import lotto.domain.dao.ResultDAO;
import lotto.domain.dao.WinningLottoDAO;
import lotto.domain.dto.LottoDTO;
import lotto.domain.dto.ResultDTO;
import lotto.domain.model.PurchasedLotto;
import lotto.domain.model.Rank;
import lotto.domain.model.WinningLotto;

import java.util.List;
import java.util.Map;

public class ResultService {

    private static final ResultService INSTANCE = new ResultService();

    private ResultService() {
    }

    public static ResultService getInstance() {
        return INSTANCE;
    }


    public void setResult(final int round, final int money) {
        ResultDTO resultDTO = new ResultDTO();

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

        ResultDAO.getInstance().setResult(resultDTO);
    }

    private double calculateProfitRate(final double money, final Map<Rank, Integer> prizeResult) {
        int prizeSum = 0;
        for (Rank rank : Rank.ranks()) {
            prizeSum += prizeResult.get(rank);
        }
        return Math.round((prizeSum / money) * 1000) / 1000.0;
    }

    private PurchasedLotto getPurchasedLotto(final int round) {
        PurchasedLotto purchasedLotto = new PurchasedLotto();
        List<LottoDTO> purchasedLottoDTOs = LottoDAO.getInstance().getPurchasedLotto(round);

        for (LottoDTO lottoDTO : purchasedLottoDTOs) {
            purchasedLotto.addLotto(lottoDTO.getLotto());
        }

        return purchasedLotto;
    }

    private WinningLotto getWinningLotto(int round) {
        return WinningLottoDAO.getInstance().getWinningLotto(round);
    }

    public ResultDTO getResult(final int newRound) {
        return ResultDAO.getInstance().getResult(newRound);
    }
}
