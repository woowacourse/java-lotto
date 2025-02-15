package lotto;

import lotto.config.ApplicationConfiguration;
import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
        LottoController lottoController = new LottoController(applicationConfiguration);
        lottoController.run();
    }
}
