package lotto;

import lotto.controller.LottoController;
import lotto.domain.ticket.strategy.RandomLottoMachine;
import lotto.service.LottoService;

public class LottoApplication {

    public static void main(String[] args) {
        RandomLottoMachine randomLottoStore = new RandomLottoMachine();
        LottoService lottoService = new LottoService(randomLottoStore);

        LottoController lottoController = new LottoController(lottoService);
        lottoController.run();
    }
}
