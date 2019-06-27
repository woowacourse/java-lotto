package lotto.controller;

import lotto.dao.LottoResultDAO;
import lotto.dao.LottoResultDAOImpl;
import lotto.domain.LottosResult;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import spark.Request;

public class LottoResultController {
    private static LottoResultDAO lottoResultDAO = LottoResultDAOImpl.getInstance();

    public static void addLottoResult(Request req) {
        WinningLottoController.addWinningLotto(req);
        WinningLotto winningLotto = WinningLottoController.getWinningLotto();

        LottosResult lottosResult = new LottosResult(winningLotto, new Lottos(LottoController.getBoughtLottos()));
        lottoResultDAO.addLottoResult(lottosResult, LottoGameController.getLastRound());
    }

    public static LottosResult getLottoResult() {
        WinningLotto winningLotto = WinningLottoController.getWinningLotto();
        Lottos lottos = new Lottos(LottoController.getBoughtLottos());

        return new LottosResult(winningLotto, lottos);
    }
}
