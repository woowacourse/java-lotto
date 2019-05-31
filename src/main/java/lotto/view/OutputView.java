package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Rank;

import java.util.List;
import java.util.Map;

import static lotto.domain.Rank.MISS;
import static lotto.domain.Rank.SECOND;

public class OutputView {

    public static void printLottos(List<Lotto> lottos , int selfSize) {
        System.out.println("수동으로 " + selfSize + "장, 자동으로 " +(lottos.size() - selfSize) + "개를 구매했습니다.");
        lottos.stream().forEach(lotto -> System.out.println(lotto));
    }

    public static void printStatistic(Map<Rank,Integer> results) {
        System.out.println("당첨 통계");
        System.out.println("----------");
        for(Rank rank : results.keySet()) {
            printResult(rank , results);
        }
    }

    private static void printResult(Rank rank, Map<Rank,Integer> results) {
        if(rank == MISS)return;

        if(rank == SECOND){
            System.out.printf("%d개 일치, 보너스 볼 일치(%l원) - %d개",rank.getCountOfMatch(),rank.getWinningMoney(),results.get(rank));
            return;
        }
        System.out.printf("%d개 일치(%l원) - %d개",rank.getCountOfMatch(),rank.getWinningMoney(),results.get(rank));

    }


    public static void printYield(double yield) {
        System.out.printf("총 수익룰은 %.1f %%입니다.",yield);
    }

}
