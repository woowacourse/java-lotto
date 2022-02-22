package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;

public class ResultView {

    private static final String PURCHASE_RESULT_MESSAGE = "개를 구매했습니다.";
    private static final String PURCHASE_RESULT_DELIMITER = ", ";
    public static final String PURCHASE_RESULT_PRINT_MESSAGE = "[%s]" + System.lineSeparator();

    public static void printLottos(List<Lotto> lottos) {
        int size = lottos.size();
        System.out.println(size + PURCHASE_RESULT_MESSAGE);
        for (Lotto lotto : lottos) {
            String numbers = lotto.getNumbers().stream()
                    .map(integer -> Integer.toString(integer))
                    .collect(Collectors.joining(PURCHASE_RESULT_DELIMITER));
            System.out.printf(PURCHASE_RESULT_PRINT_MESSAGE, numbers);
        }
        System.out.println();
    }
}
