package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Rank;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printTicket(List<Lotto> lottos) {
        int size = lottos.size();
        System.out.printf("%d개를 구매했습니다.%n", size);
        lottos.stream()
                .map(Lotto::getNumbers)
                .forEach(System.out::println);
    }

    public void printResult(Map<Rank, Integer> rankCount, double rateOfReturn) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (int i = Rank.values().length - 1; i >= 0; i--) {
            Rank rank = Rank.values()[i];
            if (rank == Rank.NONE) {
                continue;
            }
            if (rank == Rank.SECOND) {
                System.out.printf("5개 일치, 보너스 볼 일치(30000000원) - %d개%n", rankCount.getOrDefault(rank, 0));
                continue;
            }
            System.out.printf("%d개 일치 (%d원)- %d개%n", rank.getMatchCount(), rank.getPrice(), rankCount.getOrDefault(rank, 0));
        }
        System.out.printf("총 수익률은 %.2f입니다.%n", rateOfReturn);
    }
}
