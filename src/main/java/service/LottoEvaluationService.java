package service;

import factory.LottoFactory;
import java.util.List;
import model.Lotto;
import model.LottoResults;
import model.Lottos;
import model.WinningNumbers;

public class LottoEvaluationService {
    private final LottoFactory lottoFactory;

    public LottoEvaluationService(LottoFactory lottoFactory) {
        this.lottoFactory = lottoFactory;
    }

    public LottoResults evaluateLottos(Lottos lottos, List<Integer> basicNumbers, int bonusNumber) {
        Lotto basicLotto = lottoFactory.createLotto(basicNumbers);
        WinningNumbers winningNumbers = new WinningNumbers(basicLotto, bonusNumber);
        LottoResults lottoResults = lottos.evaluateLottos(winningNumbers);
        return lottoResults;
    }
}
