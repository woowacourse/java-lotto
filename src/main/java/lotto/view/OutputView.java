package lotto.view;

import static lotto.domain.MatchStatistics.*;

import java.util.HashMap;

import lotto.domain.MatchStatistics;
import lotto.dto.Profit;

public class OutputView {

    public void print(String output) {
        System.out.println(output);
    }

    public void printStatics(HashMap<MatchStatistics, Integer> map) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        for (MatchStatistics key : MatchStatistics.values()) {
            if (key == NO_MATCH) {
                break;
            }
            String output = key.getMatchData();
            int count = map.getOrDefault(key, 0);
            System.out.println(output + count + "개");
        }
    }

    public void printProfit(Profit profit) {
        String print = "총 수익률은 " + String.format("%.2f", profit.rate()) + "입니다.";
        if (!profit.isProfit()) {
            print += "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        }

        System.out.println(print);
    }
}
