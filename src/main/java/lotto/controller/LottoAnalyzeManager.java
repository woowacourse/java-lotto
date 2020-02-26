package lotto.controller;

import lotto.domain.Buyer;
import lotto.domain.Money;
import lotto.domain.lottoTicket.WinningLotto;
import lotto.domain.result.LottoResult;
import lotto.domain.result.WinningValue;

import java.util.Map;

public class LottoAnalyzeManager {

    private LottoResult lottoResult;

    public LottoAnalyzeManager(Buyer buyer, WinningLotto winningLotto) {
        lottoResult = new LottoResult();
        lottoResult.calculateLottoResult(buyer.getLottos(), winningLotto);
    }

    public Map<WinningValue, Integer> analyzeLotto() {
        return lottoResult.getLottoResult();
    }

    public int analyzeRewardRate(Money money) {
        return lottoResult.calculateRewardRate(money.getMoney(), lottoResult.getLottoResult());
    }
}
