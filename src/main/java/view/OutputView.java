package view;

import domain.Lotto;
import domain.LottoGroup;
import domain.Rank;
import domain.RankAnalysis;

public class OutputView {
    public static void printLottoGroup(LottoGroup lottoGroup) {
        System.out.printf("%d 개를 구매했습니다.\n", lottoGroup.totalSize());
        for (Lotto lotto : lottoGroup) {
            System.out.println(lotto.toString());
        }
    }

    public static void printRankAnalysis(RankAnalysis analysis) {
        System.out.println("당첨 통계\n");
        System.out.println("----------");
        for (Rank rank : RankAnalysis.ANALYZED_RANKS) {
            System.out.printf("%d개 일치%s (%d 원)- %d 개\n",
                    rank.getCountOfMatch(),
                    rank.hasBonus() ? ", 보너스 볼 일치" : "",
                    rank.getWinningMoney(),
                    analysis.count(rank));
        }
        System.out.printf("총 수익률은 %.2f%%입니다.", analysis.getEarningRate());
    }
}
