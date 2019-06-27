package lotto.controller;

import lotto.dao.WinningLottoDAO;
import lotto.dao.WinningLottoDAOImpl;
import lotto.domain.lotto.WinningLotto;
import spark.Request;

public class WinningLottoController {
    private static WinningLottoDAO winningLottoDAO = WinningLottoDAOImpl.getInstance();

    public static void addWinningLotto(Request req) {
        WinningLotto winningLotto = new WinningLotto(req.queryParams("winning-numbers"), req.queryParams("winning-bonus-numbers"));
        winningLottoDAO.addWinningLotto(winningLotto, LottoGameController.getLastRound());
    }

    public static WinningLotto getWinningLotto() {
        return winningLottoDAO.findByRound(LottoGameController.getLastRound());
    }

    public static WinningLotto getWinningLotto(int round) {
        return winningLottoDAO.findByRound(round);
    }
}
