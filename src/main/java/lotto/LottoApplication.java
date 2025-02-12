package lotto;

import lotto.controller.GameController;

public class LottoApplication {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.run();
    }
}

