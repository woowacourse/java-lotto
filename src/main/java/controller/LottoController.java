package controller;

import domain.Amount;
import domain.Lottos;
import domain.factory.LottosFactory;
import domain.WinningLotto;
import domain.dto.ResultResponse;
import domain.generator.RandomGenerator;
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
        Amount amount = inputAmount();
        Lottos lottos = getRandomLottos(amount);
        WinningLotto winningLotto = inputWinningLotto();
        getResult(lottos, winningLotto, amount);
    }

    private Amount inputAmount() {
        String price = inputView.inputPrice();
        Amount amount = new Amount(price);
        outputView.printAmount(amount);
        return amount;
    }

    private Lottos getRandomLottos(Amount amount) {
        LottosFactory lottosFactory = new LottosFactory(new RandomGenerator());
        Lottos lottos = lottosFactory.from(amount);
        outputView.printLottos(lottos.getLottosDto());
        return lottos;
    }

    private WinningLotto inputWinningLotto() {
        String winningNumber = inputView.inputWinningLotto();
        String bonusNumber = inputView.inputBonusLotto();
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);
        return winningLotto;
    }

    private void getResult(Lottos lottos, WinningLotto winningLotto, Amount amount) {
        ResultResponse lottosResult = lottos.getResult(winningLotto, amount);
        outputView.printWinningStatistic(lottosResult);
    }

}
