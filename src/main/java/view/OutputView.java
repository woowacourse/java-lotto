package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.Rewards;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.w3c.dom.ls.LSOutput;

public class OutputView {

    private static final String MESSAGE_LOTTOS_NUMBER = "%d개를 구매했습니다.\n";
    private static final String MESSAGE_WINNING_STATISTIC = "당첨 통계";
    private static final String SEPERATOR_LINE = "------------";
    private static final List<String> MESSAGE_WINNING_RANKING = Arrays.asList("6개 일치 (2000000000원)- %d개\n",
            "5개 일치, 보너스 볼 일치(30000000원)- %d개\n",
            "5개 일치 (1500000원)- %d개\n",
            "4개 일치 (50000원)- %d개\n",
            "3개 일치 (5000원)- %d개\n");
    private static final String MESSAGE_YIELD = "총 수익률은 %f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    private static final String NUMBER_DELIMITER = ", ";
    private static final String LOTTO_NUMBER_FORMAT = "[%s]\n";
    private static final int NONE = 0;
    private static final int FIRST_PRIZE_INDEX = 0;
    private static final int FIFTH_PRIZE_INDEX = 5;

    public static void printLottosInformations(Lottos lottos) {

        System.out.printf(MESSAGE_LOTTOS_NUMBER, lottos.getLottos().size());
        for (Lotto lotto : lottos.getLottos()) {
            String str = lotto.getLottoNumbers()
                    .stream()
                    .map(LottoNumber::getNumber)
                    .map(String::valueOf)
                    .collect(Collectors.joining(NUMBER_DELIMITER));
            System.out.printf(LOTTO_NUMBER_FORMAT, str);
        }
    }

    private static List<Integer> refineResults(Map<Rewards, Integer> results) {
        return Arrays.stream(Rewards.values())
                .map(value -> results.getOrDefault(value, NONE))
                .collect(Collectors.toList()).subList(FIRST_PRIZE_INDEX, FIFTH_PRIZE_INDEX);
    }

    public static void printWinningStatistic(Map<Rewards, Integer> results) {
        List<Integer> numberOfPrize = refineResults(results);
        System.out.println(MESSAGE_WINNING_STATISTIC);
        System.out.println(SEPERATOR_LINE);
        IntStream.range(FIRST_PRIZE_INDEX, FIFTH_PRIZE_INDEX)
                .boxed()
                .forEach(index -> System.out.printf(MESSAGE_WINNING_RANKING.get(index), numberOfPrize.get(index)));
    }

    public static void printYield(double yield) {
        System.out.printf(MESSAGE_YIELD, yield);
    }

    private static int reverseIndex(int index) {
        return 4 - index;
    }

}
