package lotto.domain.service;

import lotto.domain.dao.LottoDAO;
import lotto.domain.model.Lotto;
import lotto.domain.utils.ShuffledNumberGenerator;

public class LottoService {
    public void addLotto(final String inputRound, final String inputPurchaseLottoCount, final String inputManualLottoCount) {
        LottoDAO lottoDao = new LottoDAO();
        int round = Integer.parseInt(inputRound);
        int purchaseLottoCount = Integer.parseInt(inputPurchaseLottoCount);
        int manualLottoCount = Integer.parseInt(inputManualLottoCount);

        int autoLottoAvailableSize = purchaseLottoCount - manualLottoCount;
        for (int i = 0; i < autoLottoAvailableSize; i++) {
            lottoDao.addLotto(new Lotto(ShuffledNumberGenerator.getShuffledNumbers()), round);
        }
    }
}
