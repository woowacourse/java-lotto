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

    public static void showLottoQuantity(int totalQuantity, int manualQuantity) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualQuantity, totalQuantity - manualQuantity);
    }

    public static void showLottos(List<Lotto> lottos) {
        lottos.stream()
                .map(Lotto::values)
                .forEach(numbers -> System.out.println(integerToString(numbers)));
    }

    private static String integerToString(List<LottoNumber> numbers) {
        return numbers.stream()
                .map(number -> String.valueOf(number.getNumber()))
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public static void printResult(Map<Rank, Integer> results, float yield) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        printSummary(results);
        System.out.printf("총 수익률은 %.2f입니다.\n", yield);
    }

    private static void printSummary(Map<Rank, Integer> results) {
        for (Rank rank : results.keySet()) {
            printRank(rank, results.get(rank));
        }
    }

    private static void printRank(Rank rank, int price) {
        if (rank.isSecond()) {
            System.out.printf("%d개 일치, 보너스 볼 일치 (%d원)- %d개\n", rank.getCount(), rank.getMoney(), price);
            return;
        }
        System.out.printf("%d개 일치 (%d원)- %d개\n", rank.getCount(), rank.getMoney(), price);
    }
}
