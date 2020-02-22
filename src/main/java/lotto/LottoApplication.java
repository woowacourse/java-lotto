package lotto;

import lotto.controller.LottoController;
import lotto.domain.ticket.strategy.LottoMachine;
import lotto.domain.ticket.strategy.RandomLottoMachine;
import lotto.service.LottoService;

public class LottoApplication {

    public static void main(String[] args) {
        LottoMachine lottoMachine = new RandomLottoMachine();
        LottoService lottoService = new LottoService(lottoMachine);

        LottoController lottoController = new LottoController(lottoService);
        lottoController.run();
    }
}
