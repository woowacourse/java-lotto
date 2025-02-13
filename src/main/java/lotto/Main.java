package lotto;

import lotto.view.InputView;
import lotto.view.OutputView;

public class Main {
    public static void main(String[] args) {

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        LottoMachine lottoMachine = new LottoMachine(inputView,outputView);

        lottoMachine.run();
    }
}
