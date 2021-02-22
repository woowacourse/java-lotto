package lottogame;

import lottogame.controller.LottoGameController;

public class Application {

    public static void main(String[] args) {
        LottoGameController lottoGameController = new LottoGameController();
        lottoGameController.play();
    }
}
