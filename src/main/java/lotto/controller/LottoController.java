package lotto.controller;

import lotto.domain.*;
import lotto.exception.LottoCustomException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Map;
import java.util.Set;

public class LottoController {

    private final InputView inputView;

    public LottoController() {
        inputView = new InputView();
    }

    public void run() {
        Money money = inputMoney();
        LottoTickets lottoTickets = buyLottoTickets(money);

        WinningTicket winningTicket = makeWinningTicket();
        Map<WinningResult, Integer> result = LottoResultMachine.confirmResult(lottoTickets, winningTicket);

        OutputView.printProfit(LottoProfit.ofProfit(result, money));
        OutputView.printTotalWinningResult(result);
        manageChange(money);
    }

    private Money inputMoney() {
        try {
            OutputView.printMoneyMessage();
            Money money = new Money(inputView.inputMoney());
            OutputView.printNumberOfTickets(money.getTicketCount());
            return money;
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputMoney();
        }
    }

    private LottoTickets buyLottoTickets(Money money) {
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.makeTicketByCount(money.getTicketCount());
        OutputView.printAllTickets(lottoTickets);
        return lottoTickets;
    }

    private WinningTicket makeWinningTicket() {
        try {
            OutputView.printWinningNumbersTitle();
            Set<LottoNumber> winningNumbers = inputView.inputWinningNumbers();
            OutputView.printBonusNumberTitle();
            LottoNumber bonusNumber = new LottoNumber(inputView.inputBonusNumber());
            return new WinningTicket(winningNumbers, bonusNumber);
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
