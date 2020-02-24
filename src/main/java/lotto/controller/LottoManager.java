package lotto.controller;

import lotto.domain.Buyer;
import lotto.domain.Money;
import lotto.domain.lottoTicket.LottoFactory;
import lotto.domain.lottoTicket.LottoNumber;
import lotto.domain.lottoTicket.WinningLotto;
import lotto.domain.result.LottoResult;
import lotto.domain.result.WinningValue;
import lotto.util.ConvertInput;

import java.util.List;
import java.util.Map;

public class LottoManager {
    private WinningLotto winningLotto;
    private LottoResult lottoResult;

    public LottoManager() {
        LottoFactory.getInstance();
    }

    public void setWinningLotto(String numbers, int bonusNumber) {
        List<LottoNumber> winningLottoNumbers = ConvertInput.convertLottoNumbers(numbers);
        this.winningLotto = new WinningLotto(winningLottoNumbers, bonusNumber);
    }

    public Map<WinningValue, Integer> analyzeLotto(Buyer buyer) {
        lottoResult = new LottoResult();
        lottoResult.calculateLottoResult(buyer.getLottos(), winningLotto);
        return lottoResult.getLottoResult();
    }


    public int analyzeRewardRate(Money money) {
        return lottoResult.calculateRewardRate(money.getMoney(), lottoResult.getLottoResult());
    }
}
