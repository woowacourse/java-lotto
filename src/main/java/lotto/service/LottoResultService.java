package lotto.service;

import lotto.dao.LottoResultDAO;

public class LottoResultService {
    public static int findPresentRound() {
        return LottoResultDAO.getInstance().findPresentRound();
    }
}
