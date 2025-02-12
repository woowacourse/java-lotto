package view;

import domain.Lotto;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {
    private static final String PURCHASE_LOTTO_SIZE_FORMAT = "%d개를 구매했습니다.%n";

    public void printPurchaseLottos(List<Lotto> lottos) {
        System.out.printf(PURCHASE_LOTTO_SIZE_FORMAT, lottos.size());
        for (Lotto lotto : lottos) {
            Set<Integer> numbers = lotto.getNumbers();
            String purchaseLottos = numbers.stream().map(String::valueOf)
                    .collect(Collectors.joining(", "));
            System.out.println("[" + purchaseLottos + "]");
        }
    }
}
