package lottogame.controller;

import lottogame.domain.LottoGame;
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
        System.out.println();
        buyManualTickets(money);
        buyAutoTickets(money);
    }

    private void buyManualTickets(Money money) {
        lottoGame.buyManualTickets(money, InputView.getManualLottoNumbers(money));
    }

    private void buyAutoTickets(Money money) {
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
