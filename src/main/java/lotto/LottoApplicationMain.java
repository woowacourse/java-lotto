package lotto;

import lotto.controller.LottoController;

public class LottoApplicationMain {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        lottoController.runGame();
    }
}
