package lotto.config;

import lotto.service.InputService;
import lotto.service.LottoService;
import lotto.view.InputView;

import java.util.Scanner;

public class ApplicationConfiguration {
    private final Scanner scanner = new Scanner(System.in);
    private final InputView inputView = new InputView(scanner);
    //    private final OutputView outputView;
    private final InputService inputService = new InputService(inputView);
    private final LottoService lottoService = new LottoService();

    public InputService getInputService() {
        return inputService;
    }

    public LottoService getLottoService() {
        return lottoService;
    }
}
