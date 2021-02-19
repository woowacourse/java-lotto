package lotto.controller;

import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.domain.ticketFactory.FixedNumbersGenerator;
import lotto.domain.ticketFactory.TicketFactory;
import lotto.exception.LottoCustomException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final TicketFactory ticketFactory;
    private final LottoResult lottoResult;

    private Money money;
    private LottoTickets lottoTickets;

    public LottoController() {
        inputView = new InputView();
        ticketFactory = new TicketFactory();
        lottoResult = new LottoResult();
    }

    public void run() {
        money = inputMoney();
        lottoTickets = buyTickets();

        showResult(new WinningLotto(inputWinningNumbers(),inputBonus()));
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
            FixedNumbersGenerator fixedNumbersGenerator = new FixedNumbersGenerator(inputView.inputNumbers());
            return ticketFactory.makeTicket(fixedNumbersGenerator.generateNumbers());
        } catch (LottoCustomException exception) {
            OutputView.printErrorMessage(exception);
            return inputWinningNumbers();
        }
    }

    private LottoNumber inputBonus() {
        try {
            OutputView.printBonusNumber();
            return new LottoNumber(inputView.inputValue());
        } catch (LottoCustomException exception) {
            OutputView.printErrorMessage(exception);
            return inputBonus();
        }
    }

    private void showResult(WinningLotto winningLotto) {
        lottoResult.checkWinnings(lottoTickets,winningLotto);

        OutputView.printWinningResultTitle();
        OutputView.printProfit(money.calculateProfit(lottoResult.calculateTotalReward()),lottoResult.getResults());
    }
}
