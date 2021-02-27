package lotto.controller;

import lotto.domain.*;
import lotto.exception.LottoCustomException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;

public class LottoController {

    private final InputView inputView;

    public LottoController() {
        inputView = new InputView();
    }

    public void run() {
        final Money money = inputMoney();
        final Purchase purchase = inputManualPurchase(money);
        final Lottos lottos = createLottoTickets(purchase);
        final WinningTicket winningTicket = makeWinningTicket();
        final Map<Rank, Integer> result = LottoResultMachine.confirmResult(lottos, winningTicket);

        OutputView.printTotalWinningResult(result);
        OutputView.printProfit(LottoProfit.ofProfit(result, money));
        manageChange(money);
    }

    private Money inputMoney() {
        try {
            return new Money(inputView.inputMoney());
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputMoney();
        }
    }

    private Purchase inputManualPurchase(Money money) {
        try {
            return new Purchase(money, inputView.inputCountOfPurchaseManually());
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputManualPurchase(money);
        }
    }

    private Lottos createLottoTickets(Purchase purchase) {
        Lottos lottos = new Lottos();
        if (purchase.existManualPurchase()) {
            LottoFactory.createManualLottos(lottos, inputManualNumbers(purchase));
        }
        LottoFactory.createAutoLottos(lottos, purchase.getAutoPurchase());
        OutputView.printAllTickets(purchase, lottos);
        return lottos;
    }

    private List<Lotto> inputManualNumbers(Purchase purchase) {
        try {
            return inputView.inputManualNumbers(purchase.getManualPurchase());
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputManualNumbers(purchase);
        }
    }

    private WinningTicket makeWinningTicket() {
        try {
            final Lotto lotto = new Lotto(inputView.inputWinningNumbers());
            final LottoNumber bonusNumber = LottoNumber.from(inputView.inputBonusNumber());
            return new WinningTicket(lotto, bonusNumber);
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeWinningTicket();
        }
    }

    private void manageChange(Money money) {
        if (money.hasChange()) {
            OutputView.printAboutChange(money.getChange());
        }
    }
}
