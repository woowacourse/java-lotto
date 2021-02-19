package lottogame.controller;

import lottogame.domain.LottoGame;
import lottogame.domain.Money;
import lottogame.view.InputView;
import lottogame.view.OutputView;

public class LottoGameController {

    private final LottoGame lottoGame;

    public LottoGameController(final LottoGame lottoGame) {
        this.lottoGame = lottoGame;
    }

    public void buyTickets() {
        lottoGame.buyTickets(new Money(InputView.getMoneyInput()));
    }

    public void drawWinningNumber() {
        lottoGame
            .drawWinningNumber(InputView.getWinningNumbersInput(), InputView.getBonusNumberInput());
    }

    public void printLottoTickets() {
        OutputView.printLottoTickets(lottoGame.getBoughtTickets());
    }

    public void printLottoGameResult() {
        OutputView.printLottoGameResult(lottoGame.getLottoGameResult());
        OutputView.printLottoGameYield(lottoGame.getLottoGameYield());
    }
}
