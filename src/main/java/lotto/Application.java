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
        inputMoney(lottoMachine);
        registerLottoes(lottoMachine);
        Lottoes lottoes = issueLottoes(lottoMachine);
        WinnerLotto winnerLotto = inputWinnerLotto();
        summarize(lottoes, winnerLotto);
    }

    private static LottoMachine createLottoMachine() {
        return new LottoMachine(LottoGenerator.randomLottoGenerator());
    }

    private static void inputMoney(LottoMachine lottoMachine) {
        InputMoneyController controller = new InputMoneyController(lottoMachine);
        controller.run();
    }

    private static void registerLottoes(LottoMachine lottoMachine) {
        RegisterLottoController controller = new RegisterLottoController(lottoMachine);
        controller.run();
    }

    private static Lottoes issueLottoes(LottoMachine lottoMachine) {
        IssueLottoController controller = new IssueLottoController(lottoMachine);
        Lottoes issuedLottoes = controller.run();
        return issuedLottoes;
    }

    private static WinnerLotto inputWinnerLotto() {
        InputWinnerLottoController controller = new InputWinnerLottoController();
        WinnerLotto winnerLotto = controller.run();
        return winnerLotto;
    }

    private static void summarize(Lottoes lottoes, WinnerLotto winnerLotto) {
        SummarizeController controller = new SummarizeController(winnerLotto, lottoes);
        controller.run();
    }
}
