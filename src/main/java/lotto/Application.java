package lotto;

import lotto.controller.InputMoneyController;
import lotto.controller.InputWinnerLottoController;
import lotto.controller.IssueLottoController;
import lotto.controller.RegisterLottoController;
import lotto.controller.SummarizeController;
import lotto.model.LottoGenerator;
import lotto.model.LottoMachine;
import lotto.model.Lottoes;
import lotto.model.WinnerLotto;

public class Application {

    public static void main(String[] args) {
        LottoMachine lottoMachine = createLottoMachine();
        new InputMoneyController(lottoMachine).run();
        new RegisterLottoController(lottoMachine).run();
        Lottoes lottoes = new IssueLottoController(lottoMachine).run();
        WinnerLotto winnerLotto = new InputWinnerLottoController().run();
        new SummarizeController(winnerLotto, lottoes).run();
    }

    private static LottoMachine createLottoMachine() {
        return new LottoMachine(LottoGenerator.randomLottoGenerator());
    }
}
