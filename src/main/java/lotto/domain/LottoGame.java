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
        Lotto winningLotto = InputView.inputLastWeekWinningNumbers();
        LottoNumber bonusNumber = InputView.inputBonusNumber();
        WinningRanks winningRanks = compareWithWinningNumbers(lottos, winningLotto, bonusNumber);
        return new Result(winningRanks, purchaseMoney);
    }

    private WinningRanks compareWithWinningNumbers(List<Lotto> lottos, Lotto winningLotto, LottoNumber bonusNumber) {
        WinningRanks winningRanks = new WinningRanks(new HashMap<>());
        //todo: 함수를 더 간결하게 정리
        for (Lotto lotto : lottos) {
            int matchingNumber = lotto.matchWinningNumbers(winningLotto);
            if (Rank.isValid(matchingNumber)) {
                Rank rank = Rank.valueOf(matchingNumber, lotto.matchBonusNumber(bonusNumber));
                winningRanks.addWinningRanks(rank);
            }
        }
        return winningRanks;
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
