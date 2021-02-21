package lotto;

import lotto.controller.LottoController;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();
        LottoController lottoController = new LottoController(inputView, outputView);
        lottoController.start();
    }

}
