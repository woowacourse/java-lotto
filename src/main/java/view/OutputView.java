package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.Money;
import domain.Rewards;
import domain.WinningChecker;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String MESSAGE_LOTTOS_NUMBER = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String MESSAGE_WINNING_STATISTIC = "당첨 통계";
    private static final String SEPERATOR_LINE = "------------";
    private static final String MESSAGE_YIELD = "총 수익률은 %2f입니다.(1초과 시 이득, 1미만일 시 손해라는 의미임)";
    private static final String NUMBER_DELIMITER = ", ";
    private static final String LOTTO_NUMBER_FORMAT = "[%s]\n";

    public static void printLottosInformation(Money money, Lottos lottos) {

        System.out.printf(MESSAGE_LOTTOS_NUMBER, money.getManualAmount(), money.getAutoAmount());

        for (Lotto lotto : lottos.getLottos()) {
            String str = lotto.getLottoNumbers()
                .stream()
                .map(LottoNumber::getLottoNumber)
                .map(String::valueOf)
                .collect(Collectors.joining(NUMBER_DELIMITER));
            System.out.printf(LOTTO_NUMBER_FORMAT, str);
        }
    }

    public static void printWinningStatistic(Map<String, Integer> statisticMap) {
        System.out.println(MESSAGE_WINNING_STATISTIC);
        System.out.println(SEPERATOR_LINE);

        statisticMap.forEach(System.out::printf);
    }

    public static void printYield(double yield) {
        System.out.printf(MESSAGE_YIELD, yield);
    }

}
