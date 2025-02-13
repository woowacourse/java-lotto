package domain.formatter;

import domain.enums.WinningCase;
import java.util.Map;
import java.util.Map.Entry;

public class WinningCalculateFormatter {
    private static final String WINNING_CALCULATE_START = "\n당첨 통계\n--------";
    private static final String EARN_MONEY_RATIO = "총 수익률은 %f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";

    public static String winningResultFormatting(Map<WinningCase, Integer> winningResult, double earnMoneyRatio) {
        StringBuilder stringBuilder = new StringBuilder(WINNING_CALCULATE_START);
        for (Entry<WinningCase, Integer> winningCaseIntegerEntry : winningResult.entrySet()) {
            WinningCase winningCase = winningCaseIntegerEntry.getKey();
            stringBuilder.append(winningCase.formatting(winningCaseIntegerEntry.getValue()))
                    .append("\n");
        }
        stringBuilder.append(String.format(EARN_MONEY_RATIO,earnMoneyRatio));
        return stringBuilder.toString();
    }
}
