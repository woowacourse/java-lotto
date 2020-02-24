package lotto.controller;

import lotto.domain.Buyer;
import lotto.domain.Money;
import lotto.domain.lottoTicket.Lotto;
import lotto.domain.lottoTicket.LottoNumber;
import lotto.domain.lottoTicket.WinningLotto;
import lotto.domain.result.LottoResult;
import lotto.domain.result.WinningValue;
import lotto.util.ConvertInput;

import java.util.List;
import java.util.Map;

public class LottoManager {
    private final Money money;
    private final Buyer buyer;
    private WinningLotto winningLotto;
    private LottoResult lottoResult;

    public LottoManager(int money) {
        this.money = new Money(money);
        this.buyer = new Buyer(this.money.calculateLottoTicketCount());
    }

    public void setWinningLotto(String numbers, int bonusNumber) {
        List<LottoNumber> winningLottoNumbers = ConvertInput.convertLottoNumbers(numbers);
        this.winningLotto = new WinningLotto(winningLottoNumbers, bonusNumber);
    }

    public Map<WinningValue, Integer> analyzeLotto() {
        lottoResult = new LottoResult();
        lottoResult.calculateLottoResult(buyer.getLottos(), winningLotto);
        return lottoResult.getLottoResult();
    }

    public int analyzeRewardRate() {
        return lottoResult.calculateRewardRate(money.getMoney(), lottoResult.getLottoResult());
    }

    public int getLottoTicketCount() {
        return money.calculateLottoTicketCount();
    }

    public List<Lotto> getLottos(){
        return buyer.getLottos();
    }
}
