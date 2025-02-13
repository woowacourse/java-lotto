package lotto.view;

import lotto.domain.LottoRank;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {

    private OutputView() {

    }

    public static void printCountMessage(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printLottos(List<Integer> lottos) {
        Collections.sort(lottos);
        System.out.println(lottos);
    }

    public static void printWinningStatics(Map<LottoRank, Integer> winningInfo) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        Map<LottoRank, String> rankInfo = LottoRank.getRankInfo();

        for (LottoRank lottoRank : rankInfo.keySet()) {
            System.out.println(rankInfo.get(lottoRank) + "- " + winningInfo.getOrDefault(lottoRank, 0) + "개");
        }
    }

    public static void printProfitRate(double profitRate) {
        System.out.printf("총 수익률은 %.2f입니다.", profitRate);
    }
}
