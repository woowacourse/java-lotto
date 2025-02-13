package lotto;

import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Main {
    public static void main(String[] args) {

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoService lottoService = new LottoService();

        LottoMachine lottoMachine = new LottoMachine(inputView, outputView, lottoService);


        lottoMachine.run();
    }
}
