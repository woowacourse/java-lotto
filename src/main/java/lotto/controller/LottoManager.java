package lotto.controller;

import lotto.domain.Buyer;
import lotto.domain.MoneyManager;
import lotto.domain.SplitLottoNumbers;
import lotto.domain.WinningLotto;

import java.util.List;

public class LottoManager {
    public MoneyManager moneyManager;
    public Buyer buyer;
    public WinningLotto winningLotto;

    public LottoManager(int money) {
        this.moneyManager = new MoneyManager(money);
        this.buyer = new Buyer(moneyManager.purchase());
    }

    public void setWinningLotto(String numbers, int bonusNumber) {
        List<Integer> winningLottoNumbers = SplitLottoNumbers.splitLottoNumbers(numbers);
        this.winningLotto = new WinningLotto(winningLottoNumbers, bonusNumber);
    }
}
