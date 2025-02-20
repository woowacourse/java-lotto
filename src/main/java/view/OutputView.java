package view;

import domain.Lotto;
import domain.LottoStats;
import domain.Rank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class OutputView {
    public static void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getLottoDto()
                    .numbers());
        }
    }

    public static void printLottoStats(LottoStats lottoStats) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        List<Rank> ranks = Arrays.stream(Rank.values())
                .filter(Rank::isNotNone)
                .sorted(Comparator.comparingLong(Rank::getPrize))
                .toList();
        for (Rank rank : ranks) {
            System.out.println(printLottoStatsParser(rank, lottoStats));
        }
    }

    private static String printLottoStatsParser(Rank rank, LottoStats lottoStats) {
        if (rank.isSecond()) {
            return String.format("%d개 일치, 보너스 볼 일치(%d원) - %d개", rank.getCount(), rank.getPrize(), lottoStats.getStatus(rank));
        }
        return String.format("%d개 일치 (%d원) - %d개", rank.getCount(), rank.getPrize(), lottoStats.getStatus(rank));
    }

    public static void getEarningRateMessage(LottoStats lottoStats, int purchaseAmount) {
        System.out.printf("총 수익률은 %s입니다.", lottoStats.getEarningRate(purchaseAmount));
    }
}
