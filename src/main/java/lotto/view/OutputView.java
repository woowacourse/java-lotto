package lotto.view;

import static lotto.domain.MatchInfo.*;

import java.util.Map;

import lotto.domain.MatchInfo;
import lotto.domain.Profit;

public class OutputView {

    public void print(String output) {
        System.out.println(output);
    }

    public void printStatics(Map<MatchInfo, Integer> map) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        for (MatchInfo key : MatchInfo.values()) {
            if (key == NO_MATCH) {
                break;
            }
            String output = key.toString();
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
