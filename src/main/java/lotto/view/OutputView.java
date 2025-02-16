package lotto.view;

import static lotto.domain.MatchInfo.*;

import java.util.Map;

import lotto.domain.MatchInfo;
import lotto.domain.Profit;

public class OutputView {
    public static final String REQUEST_BONUS = "보너스 볼을 입력해주세요.";
    public static final String REQUEST_CASHIER = "구입금액을 입력해 주세요.";
    public static final String REQUEST_WINNING_LOTTO = "지난 주 당첨 번호를 입력해 주세요.";

    public void print(String output) {
        System.out.println(output);
    }

    public void print(int number) {
        System.out.println(number + "개를 구매했습니다.\n");
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
