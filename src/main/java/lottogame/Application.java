package lottogame;

import lottogame.controller.LottoGameController;
import lottogame.domain.LottoGame;

public class Application {

    public static void main(String[] args) {

        LottoGameController lottoGameController = new LottoGameController(new LottoGame());
        lottoGameController.buyTickets();
        lottoGameController.printLottoTickets();
        lottoGameController.drawWinningNumber();
        lottoGameController.printLottoGameResult();
    }
}
