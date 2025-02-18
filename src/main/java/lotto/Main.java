package lotto;

import lotto.service.LottoManager;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Main {
    public static void main(String[] args) {

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoManager lottoManager = new LottoManager();

        LottoMachine lottoMachine = new LottoMachine(inputView, outputView, lottoManager);
        lottoMachine.run();
    }
}
