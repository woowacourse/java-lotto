package lotto;

import lotto.controller.LottoController;
import lotto.view.input.InputView;
import lotto.view.input.reader.ConsoleReader;
import lotto.view.input.reader.Reader;
import lotto.view.output.OutputView;

public class LottoApplication {

    public static void main(final String[] args) {
        final LottoApplication lottoApplication = new LottoApplication();
        lottoApplication.run();
    }

    private void run() {
        final Reader reader = new ConsoleReader();
        final InputView inputView = new InputView(reader);
        final OutputView outputView = new OutputView();
        final LottoController lottoController = new LottoController(inputView, outputView);
        lottoController.run();
    }

}
