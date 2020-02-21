package lotto.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {

    // public void play() {
    //     List<Lotto> lottos = purchaseLottos();
    //     Result result = produceResult(lottos);
    //
    //     //통계하기
    //     int totalWinningMoney = TotalWinningMoneyCalculator.calculate(winningMoneys);
    //     OutputView.printStatistics(result);
    //     int earningRate = EarningRateCalculator.calculate(totalWinningMoney, purchaseAmount);
    //     OutputView.printEarningRate(earningRate);
    //
    // }
    //
    // private Result produceResult(List<Lotto> lottos) {
    //     List<Integer> winningNumbers = InputView.inputLastWeekWinningNumbers();
    //     int bonusBall = InputView.inputBonusBall();
    //     return compareWithWinningNumbers(lottos, winningNumbers, bonusBall);
    // }
    //
    // private Result compareWithWinningNumbers(List<Lotto> lottos, List<Integer> winningNumbers, int bonusBall) {
    //     //당첨 하기
    //     List<Integer> winningMoneys = new ArrayList<>();
    //     Result result = new Result(new HashMap<>());
    //     //todo: 함수를 더 간결하게 정리
    //     for (Lotto lotto : lottos) {
    //         int matchingNumber = LottoComparator.compare(lotto, winningNumbers);
    //         if (Rank.isValid(matchingNumber)) {
    //             Rank rank = Rank.valueOf(matchingNumber, lotto.matchBonusBall(bonusBall));
    //             result.addWinningResult(rank);
    //
    //             int winningMoney = rank.calculateWinningMoney();
    //             winningMoneys.add(winningMoney);
    //         }
    //     }
    //     return result;
    // }
    //
    // private List<Lotto> purchaseLottos() {
    //     int purchaseAmount = inputPurchaseAmount();
    //     int lottosSize = Lotto.convertMoneyToLottosSize(purchaseAmount);
    //     return generateLottos(lottosSize);
    // }
    //
    // private List<Lotto> generateLottos(int lottosSize) {
    //     List<List<Integer>> lottosNumbersList = convertToLottosNumbersList(lottosSize);
    //     List<Lotto> lottos = LottosGenerator.generate(lottosSize, lottosNumbersList);
    //     printLottos(lottos);
    //     return lottos;
    // }
    //
    // private void printLottos(List<Lotto> lottos) {
    //     for (Lotto lotto : lottos) {
    //         OutputView.printLotto(lotto);
    //     }
    // }
    //
    // private List<List<Integer>> convertToLottosNumbersList(int lottosSize) {
    //     List<List<Integer>> lottosNumbersList = new ArrayList<>(new ArrayList<>());
    //     for (int i = 0; i < lottosSize; i++) {
    //         lottosNumbersList.add(LottoNumbersGenerator.generate());
    //     }
    //     return lottosNumbersList;
    // }
    //
    // private int inputPurchaseAmount() {
    //     // 구매금액 받아서 받은거 보여주기
    //     int purchaseAmount = InputView.inputPurchaseAmount();
    //     OutputView.printLottosSize(purchaseAmount);
    //     return purchaseAmount;
    // }

}
