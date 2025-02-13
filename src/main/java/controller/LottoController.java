package controller;

import domain.Amount;
import domain.Lottos;
import domain.LottosFactory;
import domain.WinningLotto;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        String price = inputView.inputPrice();
        Amount amount = new Amount(price);
        outputView.printAmount(amount);

        LottosFactory lottosFactory = new LottosFactory();
        Lottos lottos = lottosFactory.from(amount);

        outputView.printLottos(lottos.getLottosDto());

        String winningNumber = inputView.inputWinningLotto();
        String bonusNumber = inputView.inputBonusLotto();
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        outputView.printWinningStatistic();
        lottos.getResult(winningLotto);
    }


}
