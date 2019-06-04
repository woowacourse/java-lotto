package lotto.controller;

import lotto.domain.WinningLotto;
import lotto.domain.WinningResult;
import lotto.domain.lottomanager.BonusBall;
import lotto.domain.lottomanager.shufflerule.RandomShuffle;
import lotto.domain.user.PurchaseAmount;
import lotto.domain.user.UserTickets;
import lotto.view.inputview.InputView;
import lotto.view.inputview.PriceParser;
import lotto.view.inputview.WinningNumParser;
import lotto.view.outputview.OutputView;

public class LottoShop {
    public void operate() {
        Integer purchasePrice = getPurchasePrice();
        PurchaseAmount purchaseAmount = getPurchaseAmount(purchasePrice);
        OutputView.printAmount(purchaseAmount);

        UserTickets userTickets = new UserTickets(purchaseAmount, new RandomShuffle());
        OutputView.printUserLottoTickets(userTickets);

        WinningLotto winningLotto = getWinningLotto();
        BonusBall bonusBall = getBonusBall(winningLotto);

        WinningResult winningResult = getWinningResult(userTickets, winningLotto, bonusBall);
        OutputView.printWinningStatistics(winningResult, purchasePrice);
    }

    private Integer getPurchasePrice() {
        return PriceParser.getPurchasePrice(InputView.inputPrice());
    }

    private PurchaseAmount getPurchaseAmount(Integer purchasePrice) {
        return PurchaseAmount.createLottoAmount(purchasePrice);
    }

    private WinningLotto getWinningLotto() {
        return WinningLotto.createWinningLotto(WinningNumParser.getWinningNum(InputView.inputWinningNum()));
    }

    private BonusBall getBonusBall(WinningLotto winningLotto) {
        return BonusBall.createBonusBall(InputView.inputBonusBall(), winningLotto);
    }

    private WinningResult getWinningResult(UserTickets userTickets, WinningLotto winningLotto, BonusBall bonusBall) {
        return WinningResult.createWinningResult(userTickets, winningLotto, bonusBall);
    }
}
