package lotto.domain;

import java.util.HashMap;
import java.util.List;

import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {

    public void play() {
        Money purchaseAmount = inputPurchaseAmount();
        List<Lotto> lottos = purchaseLottos(purchaseAmount);
        Result result = produceResult(lottos, purchaseAmount);
        OutputView.printResult(result);

    }

    private Result produceResult(List<Lotto> lottos, Money purchaseMoney) {
        WinningLotto winningLotto = InputView.inputWinningLotto();
        WinningRanks winningRanks = compareWithWinningNumbers(lottos, winningLotto);
        return new Result(winningRanks, purchaseMoney);
    }

    private WinningRanks compareWithWinningNumbers(List<Lotto> lottos, WinningLotto winningLotto) {
        Ranks ranks = new Ranks();
        //todo: 함수를 더 간결하게 정리
        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            ranks.add(rank);
        }
        return WinningRanksFactory.create(ranks);
    }

    private List<Lotto> purchaseLottos(Money purchaseAmount) {
        return generateLottos(purchaseAmount.toLottosSize());
    }

    private List<Lotto> generateLottos(int lottosSize) {
        List<Lotto> lottos = LottosGenerator.generate(lottosSize);
        printLottos(lottos);
        return lottos;
    }

    private void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            OutputView.printLotto(lotto);
        }
    }

    private Money inputPurchaseAmount() {
        Money purchaseAmount = InputView.inputPurchaseAmount();
        OutputView.printLottosSize(purchaseAmount.toLottosSize());
        return purchaseAmount;
    }

}
