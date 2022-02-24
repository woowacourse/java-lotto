package view;

import domain.Lotto;
import domain.Lottos;
import domain.Rewards;
import java.util.stream.Collectors;

public class OutputView {

    private static final String MESSAGE_LOTTOS_NUMBER = "%d개를 구매했습니다.\n";
    private static final String MESSAGE_WINNING_STATISTIC = "당첨 통계";
    private static final String SEPERATOR_LINE = "------------";
    private static final String[] MESSAGE_WINNING_RANKING = {"3개 일치 (5000원)- %d개\n",
            "4개 일치 (50000원)- %d개\n",
            "5개 일치 (1500000원)- %d개\n",
            "5개 일치, 보너스 볼 일치(30000000)- %d개\n",
            "6개 일치 (2000000000원)- %d개\n"};
    private static final String MESSAGE_YIELD = "총 수익률은 %f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    private static final String NUMBER_DELIMITER = ", ";
    private static final String LOTTO_NUMBER_FORMAT = "[%s]\n";

    public static void printLottosInformations(Lottos lottos) {

        System.out.printf(MESSAGE_LOTTOS_NUMBER, lottos.getLottos().size());

        for (Lotto lotto : lottos.getLottos()) {
            String str = lotto.getLottoNumbers()
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(NUMBER_DELIMITER));
            System.out.printf(LOTTO_NUMBER_FORMAT, str);
        }
    }

    public static void printWinningStatistic() {
        System.out.println(MESSAGE_WINNING_STATISTIC);
        System.out.println(SEPERATOR_LINE);

        for (int i = 0; i < 5; i++) {
            System.out.printf(MESSAGE_WINNING_RANKING[i], Rewards.getCount(Rewards.values()[reverseIndex(i)]));
        }
    }

    private static int reverseIndex(int index) {
        return 4 - index;
    }

    public static void printYield(double yield) {
        System.out.printf(MESSAGE_YIELD, yield);
    }
}
