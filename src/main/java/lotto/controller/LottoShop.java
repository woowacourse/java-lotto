package lotto.controller;

import lotto.domain.WinningLotto;
import lotto.domain.lottofactory.LottoTicket;
import lotto.domain.lottofactory.shufflerule.RandomShuffle;
import lotto.domain.user.PurchaseAmount;
import lotto.domain.user.UserTicketManager;
import lotto.view.inputview.InputView;
import lotto.view.inputview.PriceParser;
import lotto.view.inputview.WinningNumParser;
import lotto.view.outputview.OutputView;

import java.util.List;

public class LottoShop {
    public void operate() {
        Integer purchasePrice = PriceParser.getPurchasePrice(InputView.inputPrice());
        PurchaseAmount purchaseAmount = PurchaseAmount.createLottoAmount(purchasePrice);
        OutputView.printAmount(purchaseAmount);

        UserTicketManager userTicketManager = new UserTicketManager(purchaseAmount, new RandomShuffle());
        List<LottoTicket> userLottoTickets = userTicketManager.getUserLottoTickets();
        OutputView.printUserLottoTickets(userLottoTickets);

        WinningLotto winningLotto = WinningLotto.createWinningLotto(WinningNumParser.getWinningNum(InputView.inputWinningNum()));
        userTicketManager.getMatchOfCounts(winningLotto);
    }
}
