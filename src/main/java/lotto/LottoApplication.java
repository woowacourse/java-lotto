package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoService;

public class LottoApplication {

    public static void main(String[] args) {
        LottoService lottoService = new LottoService();

        LottoController lottoController = new LottoController(lottoService);
        lottoController.run();
    }
}
