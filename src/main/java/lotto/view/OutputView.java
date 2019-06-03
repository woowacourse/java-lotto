package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

import java.util.List;

import static lotto.domain.Rank.MISS;
import static lotto.domain.Rank.SECOND;

public class OutputView {

    public static void printLottos(List<Lotto> lottos, int countOfSelf) {
        System.out.println("수동으로 " + countOfSelf + "장, 자동으로 " + (lottos.size() - countOfSelf) + "개를 구매했습니다.");
        lottos.stream().forEach(lotto -> System.out.println(lotto));
    }

    public static void printStatistic(LottoResult lottoResult) {
        System.out.println("당첨 통계");
        System.out.println("----------");
        for (Rank rank : Rank.values()) {
            System.out.printf(printResult(rank), rank.getCountOfMatch(), rank.getWinningMoney(), lottoResult.getCountOfRanker(rank));
        }
    }

    private static String printResult(Rank rank) {
        if (rank == MISS) {
            return "";
        }
        if (rank == SECOND) {
            return "%d개 일치, 보너스 볼 일치(%d원) - %d개\n";
        }
        return "%d개 일치(%d원) - %d개\n";

    }

    public static void printYield(double yield) {
        System.out.printf("총 수익룰은 %.1f %%입니다.", yield);
    }

}
