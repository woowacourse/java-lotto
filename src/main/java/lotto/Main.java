package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.service.RandomLottoServiceImpl;

public class Main {

    public static void main(String[] args) {
        LottoService lottoService = new LottoService(new RandomLottoServiceImpl());
        LottoController controller = new LottoController(lottoService);
        controller.run();
    }


}
