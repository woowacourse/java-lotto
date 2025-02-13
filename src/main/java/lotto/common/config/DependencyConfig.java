package lotto.common.config;

import lotto.controller.Controller;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class DependencyConfig {
    private Controller controller;

    public DependencyConfig() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoService lottoService = new LottoService();
        controller = new Controller(inputView, outputView, lottoService);
    }

    public Controller getController() {
        return controller;
    }
}
