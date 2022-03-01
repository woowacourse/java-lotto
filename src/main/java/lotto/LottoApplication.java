package lotto;

import lotto.controller.LottoController;
import lotto.view.LottoView;
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
        final LottoView lottoView = new LottoView(inputView, outputView);
        final LottoController lottoController = new LottoController(lottoView);
        lottoController.run();
    }

}
