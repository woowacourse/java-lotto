package lotto;

import lotto.controller.LottoController;
import lotto.domain.ticket.strategy.LottoMachine;
import lotto.domain.ticket.strategy.ManualLottoMachine;
import lotto.domain.ticket.strategy.RandomLottoMachine;
import lotto.service.LottoService;

public class LottoApplication {

    public static void main(String[] args) {
        LottoMachine randomLottoMachine = new RandomLottoMachine();
        LottoMachine manualLottoMachine = new ManualLottoMachine();
        LottoService lottoService = new LottoService(randomLottoMachine, manualLottoMachine);

        LottoController lottoController = new LottoController(lottoService);
        lottoController.run();
    }
}
