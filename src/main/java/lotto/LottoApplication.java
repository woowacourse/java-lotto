package lotto;

import lotto.controller.LottoController;

import java.util.Scanner;

public class LottoApplication {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        lottoController.run();
    }
}
