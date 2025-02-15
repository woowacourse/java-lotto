package lotto.config;

import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Scanner;

public class ApplicationConfiguration {

    private final Scanner scanner = new Scanner(System.in);
    private final InputView inputView = new InputView(scanner);
    private final OutputView outputView = new OutputView();
    private final LottoService lottoService = new LottoService();

    public InputView getInputView() {
        return inputView;
    }

    public OutputView getOutputView() {
        return outputView;
    }

    public LottoService getLottoService() {
        return lottoService;
    }
}
