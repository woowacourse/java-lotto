package lotto;

import java.util.Scanner;
import lotto.controller.LottoController;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(new Scanner(System.in));
        LottoController lottoController = new LottoController(inputView, new OutputView());

        lottoController.start();
    }
}
