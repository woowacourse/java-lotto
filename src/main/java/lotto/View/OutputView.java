package lotto.View;

import lotto.domain.Rank;
import lotto.domain.lottoData.Lotto;
import lotto.domain.stats.LottoResults;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() { }

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
            if (elem.getKey().getCount()==5 && elem.getKey().getMoney()==30000000) {
                System.out.printf("%d개 일치, 보너스 볼 일치 (%d원)- %d개\n", elem.getKey().getCount(), elem.getKey().getMoney(), elem.getValue());
                continue;
            }
            System.out.printf("%d개 일치 (%d원)- %d개\n", elem.getKey().getCount(), elem.getKey().getMoney(), elem.getValue());
        }

//        System.out.printf("4개 일치 (50000원)- %d개\n", results.values().stream()
//                .filter(value -> value.values().equals(Rank.FOURTH))
//                .count());
//        System.out.printf("5개 일치 (1500000원)- %d개\n", results.values().stream()
//                .filter(value -> value.values().equals(Rank.THIRD))
//                .count());
//        System.out.printf("5개 일치, 보너스 볼 일치(30000000원) %d개\n", results.values().stream()
//                .filter(value -> value.values().equals(Rank.SECOND))
//                .count());
//        System.out.printf("6개 일치 (2000000000원) %d개\n", results.values()
//                .stream()
//                .filter(value -> value.values().equals(Rank.FIRST))
//                .count());
    }
}
