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
        final LottoTickets lottoTickets = buyLottoTickets(money);

        final WinningTicket winningTicket = makeWinningTicket();
        final Map<Rank, Integer> result = LottoResultMachine.confirmResult(lottoTickets, winningTicket);

        OutputView.printTotalWinningResult(result);
        OutputView.printProfit(LottoProfit.ofProfit(result, money));
        manageChange(money);
    }

    private Money inputMoney() {
        try {
            final Money money = new Money(inputView.inputMoney());
            OutputView.printNumberOfTickets(money.getTotalPurchaseCount());
            return money;
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputMoney();
        }
    }

    private LottoTickets buyLottoTickets(Money money) {
        LottoTickets lottoTickets = new LottoTickets();

        // 수동으로 티켓 생성
        final int manualCount = inputView.inputCountOfPurchaseManually();
        final List<LottoTicket> manuallyCreatedTickets = inputView.inputManualNumbers(manualCount);
        lottoTickets.addManuallyCreatedTickets(manuallyCreatedTickets);

        // 자동으로 티켓 생성
        lottoTickets.generateTicketAutomatically(money.getTotalPurchaseCount());
        OutputView.printAllTickets(lottoTickets);
        return lottoTickets;
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
