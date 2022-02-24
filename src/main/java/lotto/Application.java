package lotto;

import lotto.config.ViewConfig;
import lotto.controller.LottoController;

public class Application {

    public static void main(String[] args) {
        LottoController controller = new LottoController(ViewConfig.getInputView(),
            ViewConfig.getOutputView());
        controller.run();
    }
}
