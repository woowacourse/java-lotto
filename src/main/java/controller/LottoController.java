package controller;

import constant.WinningCount;
import domain.Lottos;
import domain.WinningLotto;
import dto.IssuedLottosDto;
import java.util.List;
import java.util.Map;
import util.RandomGenerator;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final RandomGenerator randomGenerator;

    public LottoController(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public void start() {
        Lottos purchasedLottos = issueLotto();
        IssuedLottosDto issuedLottosDto = IssuedLottosDto.from(purchasedLottos);
        OutputView.printLottoReceipt(issuedLottosDto);
        WinningLotto winningLotto = makeWinningLotto();
        Map<WinningCount, Integer> result = winningLotto.getLottosResult(purchasedLottos);
        Double earningRate = winningLotto.calculateEarningRate(purchasedLottos);
        OutputView.printLottoResult(result, earningRate);
    }

    private Lottos issueLotto() {
        int money = InputView.askMoney();
        return new Lottos(money, randomGenerator);
    }

    private WinningLotto makeWinningLotto() {
        List<Integer> numbers = InputView.askWinningLotto();
        Integer bonusNumber = InputView.askBonusNumber();
        return new WinningLotto(numbers, bonusNumber);
    }
}
