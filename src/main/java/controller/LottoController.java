package controller;

import domain.LottoPurchase;
import domain.LottoTickets;
import domain.Money;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();

    public void run() {
        Money budget = createBudgetMoney();
        LottoTickets lottoTickets = LottoPurchase.buy(budget);
        outputView.printLottoTicket(lottoTickets);
    }

    private Money createBudgetMoney() {
        try {
            return new Money(inputView.scanBudget());
        } catch (RuntimeException e) {
            outputView.printError(e);
            return createBudgetMoney();
        }
    }
}
