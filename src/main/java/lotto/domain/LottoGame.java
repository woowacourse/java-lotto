package lotto.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {

    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame();
        lottoGame.play();
    }

    public void play() {

        int purchaseAmount = InputView.inputPurchaseAmount();
        OutputView.printLottosSize(purchaseAmount);

        int lottosSize = Lotto.convertMoneyToLottosSize(purchaseAmount);

        List<List<Integer>> lottosNumbersList = new ArrayList<>(new ArrayList<>());
        for (int i = 0; i < lottosSize; i++) {
            lottosNumbersList.add(LottoNumbersGenerator.generate());
        }

        List<Lotto> lottos = LottosGenerator.generate(lottosSize, lottosNumbersList);
        for (Lotto lotto : lottos) {
            OutputView.printLotto(lotto);
        }

        List<Integer> winningNumbers = InputView.inputLastWeekWinningNumbers();
        int bonusBall = InputView.inputBonusBall();

        List<Integer> winningMoneys = new ArrayList<>();

        Result result = new Result(new HashMap<>());
        for (Lotto lotto : lottos) {
            int matchingNumber = LottoComparator.compare(lotto, winningNumbers, bonusBall);
            if (!Rank.isExists(matchingNumber)) {
                continue;
            }

            Rank rank = Rank.valueOf(matchingNumber, lotto.matchBonusBall(bonusBall));
            result.addWinningResult(rank);

            int winningMoney = rank.calculateWinningMoney();
            winningMoneys.add(winningMoney);
        }

        int totalWinningMoney = TotalWinningMoneyCalculator.calculate(winningMoneys);
        OutputView.printStatistics(result);
        int earningRate = EarningRateCalculator.calculate(totalWinningMoney, purchaseAmount);
        OutputView.printEarningRate(earningRate);

    }

}
