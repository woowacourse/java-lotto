package lotto;

import lotto.controller.LottoController;
import lotto.model.generator.AutoLottoGenerator;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new AutoLottoGenerator());
        lottoController.run();
    }
}
