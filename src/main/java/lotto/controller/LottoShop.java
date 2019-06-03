package lotto.controller;

import lotto.domain.WinningLotto;
import lotto.domain.WinningResult;
import lotto.domain.lottofactory.shufflerule.RandomShuffle;
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
        WinningResult winningResult = getWinningResult(userTickets, winningLotto);
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

    private WinningResult getWinningResult(UserTickets userTickets, WinningLotto winningLotto) {
        return WinningResult.createWinningResult(userTickets, winningLotto);
    }
}
