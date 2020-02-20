package lotto;

import lotto.controller.LottoController;
import lotto.domain.ticket.RealLottoStore;
import lotto.service.LottoService;

public class LottoApplication {
    public static void main(String[] args) {
        LottoService lottoService = new LottoService(new RealLottoStore());

        LottoController lottoController = new LottoController(lottoService);
        lottoController.run();
    }
}
