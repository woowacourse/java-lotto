package lotto.view;

import static lotto.domain.MatchRank.*;

import lotto.domain.LottoStatistics;
import lotto.domain.MatchRank;
import lotto.domain.Profit;

public class OutputView {

    public void print(String output) {
        System.out.println(output);
    }

    public void printResult(LottoStatistics statistics, Profit profit) {
        printStatics(statistics);
        printProfit(profit);
    }

    private void printStatics(LottoStatistics statistics) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        for (MatchRank rank : values()) {
            if (rank == NO_MATCH) {
                continue;
            }
            String output = rank.getMatchData();
            int count = statistics.getCountOf(rank);
            System.out.println(output + count + "개");
        }
    }

    private void printProfit(Profit profit) {
        String print = "총 수익률은 " + String.format("%.2f", profit.rate()) + "입니다.";
        if (!profit.isProfit()) {
            print += "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        }

        System.out.println(print);
    }
}
