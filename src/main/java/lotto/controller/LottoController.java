package lotto.controller;

import java.util.List;
import java.util.Scanner;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningResult;
import lotto.domain.ticketFactory.FixedNumbersGenerator;
import lotto.domain.ticketFactory.TicketFactory;
import lotto.exception.LottoCustomException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final TicketFactory ticketFactory;

    private Money money;
    private LottoTickets lottoTickets;

    public LottoController(final Scanner scanner) {
        inputView = new InputView(scanner);
        ticketFactory = new TicketFactory();
    }

    public void run() {
        money = inputMoney();
        lottoTickets = buyTickets();

        LottoTicket winningTicket = inputWinningNumbers();
        LottoNumber bonusBall = inputBonus(winningTicket);

        showResult(winningTicket, bonusBall);
    }

    private Money inputMoney() {
        try {
            OutputView.printMoneyMessage();
            Money money = new Money(inputView.inputValue());
            OutputView.printTicketCountMessage(money.countTickets());
            return money;
        } catch (LottoCustomException exception) {
            OutputView.printErrorMessage(exception);
            return inputMoney();
        }
    }

    private LottoTickets buyTickets() {
        lottoTickets = ticketFactory.makeTicketsByCount(money.countTickets());
        OutputView.printAllTickets(lottoTickets);
        return lottoTickets;
    }

    private LottoTicket inputWinningNumbers() {
        try {
            OutputView.printWinningNumbers();
            FixedNumbersGenerator fixedNumbersGenerator = new FixedNumbersGenerator(inputView.inputWinningNumbers());
            return ticketFactory.makeTicket(fixedNumbersGenerator.generateNumbers());
        } catch (LottoCustomException exception) {
            OutputView.printErrorMessage(exception);
            return inputWinningNumbers();
        }
    }

    private LottoNumber inputBonus(LottoTicket winningTicket) {
        try {
            OutputView.printBonusNumber();
            LottoNumber bonusBall = new LottoNumber(inputView.inputValue());
            winningTicket.checkDuplicateNumber(bonusBall);
            return bonusBall;
        } catch (LottoCustomException exception) {
            OutputView.printErrorMessage(exception);
            return inputBonus(winningTicket);
        }
    }

    private void showResult(LottoTicket winningTicket, LottoNumber bonusBall) {
        List<Integer> hitCounts = lottoTickets.checkHitCount(winningTicket, bonusBall);
        int totalReward = WinningResult.calculateTotalReward(hitCounts);

        OutputView.printWinningResultTitle();
        OutputView.printProfit(money.calculateProfit(totalReward),hitCounts);
    }
}
