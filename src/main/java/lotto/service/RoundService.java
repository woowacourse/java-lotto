package lotto.service;

import lotto.persistence.RoundDAO;

public class RoundService {
    public static int getThisLottoRoundId() {
        RoundDAO roundDao = RoundDAO.getInstance();
        return roundDao.getLatestRoundId();
    }
}
