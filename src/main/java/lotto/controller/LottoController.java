package lotto.controller;

import lotto.domain.*;
import lotto.domain.ticketfactory.FixedNumberTicketFactory;
import lotto.exception.LottoCustomException;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private final InputView inputView;

    public LottoController() {
        inputView = new InputView();
    }

    public void run() {
        Money money = inputMoney();
        LottoTickets lottoTickets = buyLottoTickets(money);

        LottoTicket winningTicket = makeWinningTicket();
        LottoNumber bonusBall = makeBonusNumber(winningTicket);

        showResult(money, lottoTickets, winningTicket, bonusBall);
        manageChange(money);
    }

    private Money inputMoney() {
        try {
            OutputView.printMoneyMessage();
            Money money = new Money(inputView.inputMoney());
            OutputView.printTicketCountMessage(money.getTicketCount());
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

    private LottoTicket makeWinningTicket() {
        try {
            OutputView.printWinningNumbers();
            return FixedNumberTicketFactory
                    .makeTicket(inputView.inputWinningTicket());
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeWinningTicket();
        }
    }

    private LottoNumber makeBonusNumber(LottoTicket winningTicket) {
        try {
            OutputView.printBonusNumber();
            LottoNumber bonusBall = new LottoNumber(inputView.inputBonusNumber());
            winningTicket.checkDuplicateNumber(bonusBall);
            return bonusBall;
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeBonusNumber(winningTicket);
        }
    }

    private void showResult(Money money,
                            LottoTickets lottoTickets,
                            LottoTicket winningTicket,
                            LottoNumber bonusBall) {
        List<Integer> hitCounts = lottoTickets.checkHitCount(winningTicket, bonusBall);
        int totalReward = WinningResult.calculateWinnings(hitCounts);
        OutputView.printTotalWinningResult(money.getProfit(totalReward),
                WinningResult.getWinningResult(hitCounts));
    }

    private void manageChange(Money money) {
        if (money.hasChange()) {
            OutputView.printAboutChange(money.getChange());
        }
    }
}
