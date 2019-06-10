package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Rank;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public static void printLottos(Lottos lottos, int numOfCustomLottos) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", numOfCustomLottos, lottos.size() - numOfCustomLottos);
        for (Lotto lotto : lottos.getLottos()) {
            List<String> numbers = lotto.getLottoNumbers()
                    .stream()
                    .map(ln -> String.valueOf(ln.getNumber()))
                    .collect(Collectors.toList());
            System.out.println("[" + String.join(", ",
                    numbers.toArray(new String[numbers.size()])) + "]");
        }
    }

    public static void printStatistics(Map<Rank, Integer> statistics) {
        System.out.println("\n당첨 통계");
        System.out.println("----------");

        for (Rank rank : Arrays.asList(Rank.values()).subList(1, Rank.values().length)) {
            printRank(rank, statistics.get(rank));
        }
    }

    private static void printRank(Rank rank, int num) {
        if (rank == Rank.SECOND) {
            System.out.printf("%d개 일치, 보너스 볼 일치(%d원) - %d개\n", rank.getCountOfMatch(),
                    rank.getWinningMoney(), num);
            return;
        }
        System.out.printf("%d개 일치 (%d원) - %d개\n", rank.getCountOfMatch(),
                rank.getWinningMoney(), num);
    }

    public static void printInterestRate(long interestRate) {
        System.out.println("총 수익률은 " + interestRate + "% 입니다.");
    }
}
