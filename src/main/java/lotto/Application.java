package lotto;

import lotto.controller.LottoController;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = createLottoController();

        lottoController.start();
    }

    private static LottoController createLottoController() {
        InputView inputView = new InputView(new Scanner(System.in));
        return new LottoController(inputView, new OutputView(), new Money(1000));
    }
}
