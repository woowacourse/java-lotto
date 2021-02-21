package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoStatistics;
import lotto.domain.Lottos;

import java.util.List;
import java.util.stream.Collectors;

import static lotto.view.RankMessage.getRankMessages;

public class OutputView {
    public static final String DEFAULT_STATISTICS_FORMAT = "%d개 일치 (%d원)- ";
    public static final String STATISTICS_FORMAT_FOR_SECOND = "%d개 일치, 보너스 볼 일치(%d원)- ";

    private OutputView() {
    }

    public static void showLottos(Lottos lottos) {
        System.out.printf("%d개를 구매했습니다.\n", lottos.getNumberOfLotto());
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(printLottoSummary(lotto));
        }
        System.out.println();
    }

    private static String printLottoSummary(Lotto lotto) {
        return String.format("[%s]", lottoSummary(lotto));
    }

    private static String lottoSummary(Lotto lotto) {
        return lotto.getLottoNumbers()
                .stream()
                .map(LottoNumber::getNumberAsString)
                .collect(Collectors.joining(", "));
    }

    public static void showResultStatistics(LottoStatistics lottoStatistics) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        List<Integer> statistics = lottoStatistics.getWinCountByRank();
        List<String> rankMessages = getRankMessages();
        for (int i = 0; i < statistics.size(); i++) {
            System.out.printf("%s %d개\n", rankMessages.get(i), statistics.get(i));
        }
        System.out.printf("총 수익률은 %.2f입니다.\n", lottoStatistics.getProfitRate());
    }
}
