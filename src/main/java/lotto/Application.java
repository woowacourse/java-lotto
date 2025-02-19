package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoGenerator;
import lotto.domain.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final LottoGenerator lottoGenerator = new RandomLottoGenerator();
        final LottoController lottoController = new LottoController(inputView, outputView, lottoGenerator);
        lottoController.run();
    }
}
