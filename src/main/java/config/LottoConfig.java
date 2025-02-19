package config;

import controller.LottoController;
import view.InputView;
import view.OutputView;

public class LottoConfig {
    public static LottoController createController() {
        return new LottoController(InputView.create(), OutputView.create());
    }
}
