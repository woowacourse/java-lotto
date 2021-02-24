package lottogame.controller;

import lottogame.domain.LottoGame;
import lottogame.domain.LottoManualTicketCount;
import lottogame.domain.Money;
import lottogame.view.InputView;
import lottogame.view.OutputView;

public class LottoGameController {

    private final LottoGame lottoGame;

    public LottoGameController(LottoGame lottoGame) {
        this.lottoGame = lottoGame;
    }

    public void buyTickets() {
        Money money = new Money(InputView.getMoneyInput());
        LottoManualTicketCount lottoManualTicketCount =
                new LottoManualTicketCount(InputView.getManualLottoTicketCount(), money);
        if (lottoManualTicketCount.isRemain()) {
            buyManualTicket(money, lottoManualTicketCount);
        }
        buyAutoTicket(money);
    }

    private void buyManualTicket(Money money, LottoManualTicketCount lottoManualTicketCount) {
        InputView.printManualNumbersInputRequestMessage();
        while (lottoManualTicketCount.isRemain()) {
            lottoGame.buyManualTicket(money, InputView.getManualNumbersInput());
            lottoManualTicketCount.reduce();
        }
    }

    private void buyAutoTicket(Money money) {
        lottoGame.buyAutoTickets(money);
    }

    public void printBroughtLottoTickets() {
        OutputView.printBroughtLottoTickets(lottoGame.getBroughtTickets());
    }

    public void drawWinningNumber() {
        lottoGame.drawWinningNumber(InputView.getWinningNumbersInput(), InputView.getBonusNumberInput());
    }

    public void printLottoGameResult() {
        OutputView.printLottoGameResult(lottoGame.getLottoGameResult());
        OutputView.printLottoGameYield(lottoGame.getLottoGameYield());
    }
}
