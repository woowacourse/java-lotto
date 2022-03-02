package lotto;

import lotto.view.LottoView;
import lotto.view.input.InputView;
import lotto.view.input.reader.ConsoleReader;
import lotto.view.input.reader.Reader;
import lotto.view.output.OutputView;

public class MainApplication {

    public static void main(final String[] args) {
        final MainApplication mainApplication = new MainApplication();
        mainApplication.run();
    }

    private void run() {
        final LottoView lottoView = this.initLottoView();
        final LottoApplication lottoApplication = new LottoApplication(lottoView);
        lottoApplication.run();
    }

    private LottoView initLottoView() {
        final Reader reader = new ConsoleReader();
        final InputView inputView = new InputView(reader);
        final OutputView outputView = new OutputView();
        return new LottoView(inputView, outputView);
    }

}
