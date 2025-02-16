package service;

import factory.LottoFactory;
import java.util.List;
import model.Lotto;
import model.Lottos;
import model.WinningLotto;

public class LottoEvaluationService {
    private final LottoFactory lottoFactory;

    public LottoEvaluationService(LottoFactory lottoFactory) {
        this.lottoFactory = lottoFactory;
    }

    public void evaluateLottos(Lottos lottos, List<Integer> basicNumbers, int bonusNumber) {
        Lotto basicLotto = lottoFactory.createLotto(basicNumbers);
        WinningLotto winningLotto = new WinningLotto(basicLotto, bonusNumber);
        lottos.rankAll(winningLotto);
    }
}
