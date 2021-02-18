package lottogame.view;

import lottogame.domain.Rank;
import lottogame.domain.lotto.Lotto;
import lottogame.domain.stats.LottoResults;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void showLottoQuantity(int quantity) {
        System.out.printf("%d개를 구매했습니다.\n", quantity);
    }

    public static void showLottos(List<Lotto> lottos) {
        lottos.stream()
                .map(lotto -> lotto.values())
                .forEach(numbers -> System.out.println(integerToString(numbers)));
    }

    private static String integerToString(List<Integer> numbers) {
        return numbers.stream()
                .map(number -> String.valueOf(number))
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public static void printResult(LottoResults results) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        printSummary(results);
        System.out.printf("총 수익률은 %f입니다.\n", results.getProfit());
    }

    private static void printSummary(LottoResults results) {
        for (Map.Entry<Rank, Integer> statistic : results.values().entrySet()) {
            Rank rank = statistic.getKey();
            int price = statistic.getValue();
            printRank(rank, price);
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
