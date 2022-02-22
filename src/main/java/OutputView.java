import java.util.Map;

public class OutputView {

    private static final String MESSAGE_LOTTOS_NUMBER = "%d개를 구매했습니다.";
    private static final String NUMBER_DELIMITER = ", ";

    private static final String MESSAGE_WINNING_STATISTIC = "당첨 통계";
    private static final String SEPERATOR_LINE = "------------";


    private static final String[] MESSAGE_WINNING_RANKING = {"3개 일치 (5000원)- %d개\n",
            "4개 일치 (50000원)- %d개\n",
            "5개 일치 (1500000원)- %d개\n",
            "5개 일치, 보너스 볼 일치(30000000)- %d개\n",
            "6개 일치 (2000000000원)- %d개\n"};




    private static final String MESSAGE_YIELD = "총 수익률은 %lf입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";

//    생성된 로또 번호와 개수 출력하는 기능
    public static void printLottosInformations(Lottos lottos) {

        System.out.printf(MESSAGE_LOTTOS_NUMBER, lottos.getLottos().size());

        for (Lotto lotto : lottos.getLottos()) {
            System.out.println("[" + String.join(NUMBER_DELIMITER, (CharSequence) lotto.getLottoNumbers()) + "]");
        }
    }

    public static void printWinningStatistic(Map<Integer, Integer> results) {
        System.out.println(MESSAGE_WINNING_STATISTIC);
        System.out.println(SEPERATOR_LINE);
        for (int i = 0; i<MESSAGE_WINNING_RANKING.length; ++i) {
            System.out.printf(MESSAGE_WINNING_RANKING[i], results.get(5-i));
        }
    }

    public static void printYield(double yield) {
        System.out.printf(MESSAGE_YIELD, yield);
    }
}



/*
    당첨 통계
---------
        3개 일치 (5000원)- 1개
        4개 일치 (50000원)- 0개
        5개 일치 (1500000원)- 0개
        5개 일치, 보너스 볼 일치(30000000원) - 0개
        6개 일치 (2000000000원)- 0개
        총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
 */
