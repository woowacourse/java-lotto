package service;

import model.Lotto;
import model.Lottos;
import model.WinningLotto;

public class LottoEvaluationService {
    public void evaluateLottos(Lottos lottos, Lotto basicLotto, int bonusNumber) {
        initializeWinningLotto(basicLotto, bonusNumber);
        lottos.rankAll();
    }

    private void initializeWinningLotto(Lotto basicLotto, int bonusNumber) {
        WinningLotto.initialize(basicLotto, bonusNumber);
    }
}
