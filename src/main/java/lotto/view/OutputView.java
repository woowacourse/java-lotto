package lotto.view;

import lotto.domain.LottoRank;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String PROFIT = "이익";
    private static final String LOSS = "손해";
    private static final int PROFIT_STANDARD = 1;

    private OutputView() {

    }

    public static void printCountMessage(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printLottos(final List<Integer> lottoNumbers) {
        List<Integer> sortedNumbers = lottoNumbers.stream().sorted().toList();
        System.out.println(sortedNumbers);
    }

    public static void printWinningStatics(final Map<LottoRank, Integer> winningInfo) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        Map<LottoRank, String> rankInfo = LottoRank.getRankInfo();

        for (LottoRank lottoRank : rankInfo.keySet()) {
            System.out.println(rankInfo.get(lottoRank) + "- " + winningInfo.getOrDefault(lottoRank, 0) + "개");
        }
    }

    public static void printProfitRate(final double profitRate) {
        if (profitRate >= PROFIT_STANDARD) {
            System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s이라는 의미임)", profitRate, PROFIT);
            return;
        }
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)", profitRate, LOSS);
    }
}
