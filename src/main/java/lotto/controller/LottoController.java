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
        final LottoTickets lottoTickets = createLottoTickets(purchase);

        final WinningTicket winningTicket = makeWinningTicket();
        final Map<Rank, Integer> result = LottoResultMachine.confirmResult(lottoTickets, winningTicket);

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

    private LottoTickets createLottoTickets(Purchase purchase) {
        LottoTickets lottoTickets = new LottoTickets();

        if (purchase.isManualPurchaseExist()) {
            lottoTickets.addManuallyCreatedTickets(inputManualNumbers(purchase));
        }

        lottoTickets.generateTicketAutomatically(purchase.getAutoPurchase());
        OutputView.printAllTickets(purchase, lottoTickets);
        return lottoTickets;
    }

    private List<LottoTicket> inputManualNumbers(Purchase purchase) {
        try {
            return inputView.inputManualNumbers(purchase.getManualPurchase());
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputManualNumbers(purchase);
        }
    }

    private WinningTicket makeWinningTicket() {
        try {
            final LottoTicket lottoTicket = new LottoTicket(inputView.inputWinningNumbers());
            final LottoNumber bonusNumber = new LottoNumber(inputView.inputBonusNumber());
            return new WinningTicket(lottoTicket, bonusNumber);
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
