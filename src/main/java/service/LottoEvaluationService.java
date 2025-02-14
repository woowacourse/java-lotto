package service;

import model.Lottos;
import model.WinningLotto;

public class LottoEvaluationService {

    public void evaluateLottos(Lottos lottos, WinningLotto winningLotto) {
        lottos.rankAll(winningLotto);
    }
}
