package lotto.controller;

import lotto.controller.dto.LottoTicketsDto;
import lotto.domain.Money;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;
import lotto.domain.WinningNumbers;
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
        WinningNumbers winningNumbers = createWinningNumbers();

        LottoResult lottoResult = createLottoResult(lottoTickets, winningNumbers);

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

    private WinningNumbers createWinningNumbers() {
        try {
            return new WinningNumbers(inputView.getNormalWinningNumbers(), inputView.getBonusNumber());
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
            return createWinningNumbers();
        }
    }

    private LottoResult createLottoResult(LottoTickets lottoTickets, WinningNumbers winningNumbers) {
        outputView.printLottoResultMessage();
        return lottoTickets.determine(winningNumbers);
    }
}
