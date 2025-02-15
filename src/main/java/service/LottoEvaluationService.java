package service;

import model.Lotto;
import model.Lottos;
import model.WinningLotto;

public class LottoEvaluationService {
    public void evaluateLottos(Lottos lottos, Lotto basicLotto, int bonusNumber) {
        WinningLotto winningLotto = new WinningLotto(basicLotto, bonusNumber);
        lottos.rankAll(winningLotto);
    }
}
