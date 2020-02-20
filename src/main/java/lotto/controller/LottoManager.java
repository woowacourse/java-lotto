package lotto.controller;

import lotto.domain.*;

import java.util.List;
import java.util.Map;

public class LottoManager {
    public MoneyManager moneyManager;
    public Buyer buyer;
    public WinningLotto winningLotto;
    public WinningRankResult winningRankResult;

    public LottoManager(int money) {
        this.moneyManager = new MoneyManager(money);
        this.buyer = new Buyer(moneyManager.purchase());
    }

    public void setWinningLotto(String numbers, int bonusNumber) {
        List<Integer> winningLottoNumbers = SplitLottoNumbers.splitLottoNumbers(numbers);
        this.winningLotto = new WinningLotto(winningLottoNumbers, bonusNumber);
    }

    public Map<WinningValue, Integer> analyzeLotto() {
        winningRankResult = new WinningRankResult();
        winningRankResult.checkRank(buyer.getLottos(), winningLotto);
        return winningRankResult.getWinningValueResult();
    }


}
