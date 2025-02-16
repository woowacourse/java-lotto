package lotto;

import lotto.controller.LottoController;
import lotto.domain.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final RandomLottoGenerator randomLottoGenerator = new RandomLottoGenerator();
        final LottoController lottoController = new LottoController(inputView, outputView, randomLottoGenerator);
        lottoController.run();
    }
}
