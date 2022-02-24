package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.dto.LottoDTO;

public class ResultView {
    private static final String MESSAGE_PURCHASE_COUNT = "개를 구매했습니다.";
    private static final String LOTTO_FORMAT = "[%s]%n";
    private static final String LOTTO_NUMBER_DELIMITER = ", ";

    public static void showPurchaseCount(int count) {
        System.out.println();
        System.out.println(count + MESSAGE_PURCHASE_COUNT);
    }

    public static void showLottos(List<LottoDTO> lottos) {
        for (LottoDTO lotto : lottos) {
            String joinedNumbers = lotto.getNumbers().stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(LOTTO_NUMBER_DELIMITER));
            System.out.printf(LOTTO_FORMAT, joinedNumbers);
        }
    }
}
