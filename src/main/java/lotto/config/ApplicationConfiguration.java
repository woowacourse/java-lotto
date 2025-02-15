package lotto.config;

import lotto.service.InputService;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Scanner;

public class ApplicationConfiguration {

    private final Scanner scanner = new Scanner(System.in);
    private final InputView inputView = new InputView(scanner);
    private final OutputView outputView = new OutputView();
    private final InputService inputService = new InputService(inputView, outputView);
    private final LottoService lottoService = new LottoService();

    public InputService getInputService() {
        return inputService;
    }

    public OutputView getOutputView() {
        return outputView;
    }

    public LottoService getLottoService() {
        return lottoService;
    }
}
