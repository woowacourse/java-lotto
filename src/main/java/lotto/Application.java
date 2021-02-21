package lotto;

import lotto.controller.LottoController;
import lotto.domain.lottomachine.LottoMachine;
import lotto.domain.lottomachine.RandomLottoMachine;

public class Application {

    public static void main(String[] args) {
        LottoMachine lottoMachine = new RandomLottoMachine();
        LottoController lottoController = new LottoController(lottoMachine);
        lottoController.start();
    }

}
