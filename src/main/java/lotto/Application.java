package lotto;

import lotto.controller.LottoController;
import lotto.util.NumberGenerator;
import lotto.util.RandomNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        NumberGenerator numberGenerator = new RandomNumber();
        final LottoController lottoController = new LottoController(new InputView(), new OutputView(), numberGenerator);
        lottoController.run();
    }

}
