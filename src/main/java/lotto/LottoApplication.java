package lotto;

import lotto.controller.LottoController;
import lotto.domain.ticket.LottoStore;
import lotto.domain.ticket.RealLottoStore;
import lotto.service.LottoService;

public class LottoApplication {
    private static final LottoStore lottoStore = new RealLottoStore();

    public static void main(String[] args) {
        LottoService lottoService = new LottoService(lottoStore);

        LottoController lottoController = new LottoController(lottoService);
        lottoController.run();
    }
}
