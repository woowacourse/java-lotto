package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lotto.view.OutputView;

public class LottoGame {

    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame();
        lottoGame.play();
    }

    public void play() {
        int purchaseAmount = 14000;
        int lottosSize = Lotto.convertMoneyToLottosSize(purchaseAmount);
        int firstNumber = 0;
        int secondNumber = 0;
        int thirdNumber = 0;
        int fourthNumber = 0;
        int fifthNumber = 0;
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
            if (rank.equals(Rank.FIRST)) {
                firstNumber += 1;
            }

            if (rank.equals(Rank.SECOND)) {
                secondNumber += 1;
            }

            if (rank.equals(Rank.THIRD)) {
                thirdNumber += 1;
            }

            if (rank.equals(Rank.FOURTH)) {
                fourthNumber += 1;
            }

            if (rank.equals(Rank.FIFTH)) {
                fifthNumber += 1;
            }


            int winningMoney = rank.calculateWinningMoney();
            winningMoneys.add(winningMoney);
        }

        int totalWinningMoney = TotalWinningMoneyCalculator.calculate(winningMoneys);

        int earningRate = EarningRateCalculator.calculate(totalWinningMoney, purchaseAmount);

        OutputView.printStatistic(firstNumber, secondNumber, thirdNumber, fourthNumber, fifthNumber);

    }

}
