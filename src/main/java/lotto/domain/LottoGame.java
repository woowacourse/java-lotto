package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoGame {

    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame();
        lottoGame.play();
    }

    public void play() {
        int purchaseAmount = 14000;
        int lottosSize = Lotto.convertMoneyToLottosSize(purchaseAmount);
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<List<Integer>> lottosNumbersList = new ArrayList<>(new ArrayList<>());
        for (int i = 0; i < lottosSize; i++) {
            lottosNumbersList.add(LottoNumbersGenerator.generate());
        }

        int bonusBall = 7;

        List<Lotto> lottos = LottosGenerator.generate(lottosSize, lottosNumbersList);
        List<Integer> winningMoneys = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int matchingNumber = LottoComparator.compare(lotto, winningNumbers, bonusBall);
            if (!Rank.isExists(matchingNumber)) {
                continue;
            }

            Rank rank = Rank.valueOf(matchingNumber, lotto.matchBonusBall(bonusBall));
            int winningMoney = rank.calculateWinningMoney();
            winningMoneys.add(winningMoney);
        }

        int totalWinningMoney = TotalWinningMoneyCalculator.calculate(winningMoneys);

        int earningRate = EarningRateCalculator.calculate(totalWinningMoney, purchaseAmount);

    }

}
