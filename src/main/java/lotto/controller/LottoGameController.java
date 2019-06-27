package lotto.controller;

import lotto.dao.LottoGameDAO;
import lotto.dao.LottoGameDAOImpl;

public class LottoGameController {
    private static int ADD_ONE_BEFORE_ADD_LOTTO_GAME = 1;

    private static LottoGameDAO lottoGameDAO = LottoGameDAOImpl.getInstance();

    public static int getLastRound() {
        return lottoGameDAO.getLastRound();
    }

    public static void addLottoGame() {
        lottoGameDAO.addLottoGame(lottoGameDAO.getLastRound() + ADD_ONE_BEFORE_ADD_LOTTO_GAME);
    }
}
