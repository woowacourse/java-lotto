package lotto.View;

import lotto.domain.lottoData.Lotto;

import java.util.List;
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
}
