package lottogame.view;

import lottogame.domain.stats.Rank;
import lottogame.domain.lotto.Lotto;
import lottogame.domain.lotto.LottoNumber;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void printLottoQuantity(int manualQuantity, int autoQuantity) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualQuantity, autoQuantity);
    }

    public static void printLottos(List<Lotto> lottos) {
        lottos.stream()
                .map(Lotto::values)
                .forEach(lotto -> System.out.println(lottoToString(lotto)));
    }

    private static String lottoToString(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .map(lottoNumber -> String.valueOf(lottoNumber.value()))
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public static void printSummary(Map<Rank, Integer> results, float yield) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        printResults(results);
        System.out.printf("총 수익률은 %.2f입니다.\n", yield);
    }

    private static void printResults(Map<Rank, Integer> results) {
        for (Rank rank : results.keySet()) {
            printRankInfo(rank, results.get(rank));
        }
    }

    private static void printRankInfo(Rank rank, int matchCount) {
        if (rank.isSecond()) {
            System.out.printf("%d개 일치, 보너스 볼 일치 (%d원)- %d개\n", rank.getCount(), rank.getMoney(), matchCount);
            return;
        }
        System.out.printf("%d개 일치 (%d원)- %d개\n", rank.getCount(), rank.getMoney(), matchCount);
    }
}
