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

    public static void printResult(LottoResults results) {     // LottoResults
        System.out.println("당첨 통계");
        System.out.println("---------");

        Map<Rank, Integer> statistics = results.values();
        for (Map.Entry<Rank, Integer> elem : statistics.entrySet()) {
            if (elem.getKey().getCount() == 5 && elem.getKey().getMoney() == 30000000) {
                System.out.printf("%d개 일치, 보너스 볼 일치 (%d원)- %d개\n", elem.getKey().getCount(), elem.getKey().getMoney(), elem.getValue());
                continue;
            }
            System.out.printf("%d개 일치 (%d원)- %d개\n", elem.getKey().getCount(), elem.getKey().getMoney(), elem.getValue());
        }

        System.out.printf("총 수익률은 %f입니다.\n", results.getProfit());
    }
}
