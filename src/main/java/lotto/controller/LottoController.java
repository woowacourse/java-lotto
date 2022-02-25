package lotto.controller;

import lotto.controller.dto.LottoTicketsDto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.Money;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;
import lotto.domain.WinningNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Money money = createMoney();

        LottoTickets lottoTickets = createLottoTickets(money);
        WinningNumber winningNumber = createWinningNumbers();

        LottoResult lottoResult = createLottoResult(lottoTickets, winningNumber);

        outputView.printYield(lottoResult.getRanks(), lottoResult.calculateYield(money));
    }

    private Money createMoney() {
        try {
            return new Money(inputView.getMoney());
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
            return createMoney();
        }
    }

    private LottoTickets createLottoTickets(Money money) {
        LottoTickets lottoTickets = new LottoMachine().purchase(money);

        outputView.printTotalCount(money.calculateTicketCount());
        outputView.printLottoTicketsInfo(new LottoTicketsDto(lottoTickets));

        return lottoTickets;
    }

    private WinningNumber createWinningNumbers() {
        try {
            return new WinningNumber(new LottoTicket(inputView.getNormalWinningNumbers()),
                    new LottoNumber(inputView.getBonusNumber()));
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
            return createWinningNumbers();
        }
    }

    private LottoResult createLottoResult(LottoTickets lottoTickets, WinningNumber winningNumber) {
        outputView.printLottoResultMessage();
        return lottoTickets.determine(winningNumber);
    }
}
