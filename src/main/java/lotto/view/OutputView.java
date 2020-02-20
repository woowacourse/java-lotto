package lotto.view;

import java.util.Map;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.Result;

public class OutputView {

    public static void printLottosSize(int lottosSize) {
        System.out.println(String.format("%d개를 구매했습니다.", lottosSize));
    }

    public static void printLotto(Lotto lotto) {
        System.out.println(lotto);
    }

    public static void printStatistics(Result result) {
        Map<Rank, Integer> winningResults = result.getWinningResults();
        for (Rank rank : winningResults.keySet()) {
            System.out.println(String.format("%d개 일치(%d원)- %d개", rank.getMatchNumber(), rank.calculateWinningMoney(),
                winningResults.get(rank)));
        }
    }

    public static void printEarningRate(int earningRate) {
        System.out.println(String.format("총 수익률은 %d입니다.", earningRate));
    }

}
