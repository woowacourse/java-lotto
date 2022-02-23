package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;

public class OutputView {

    private static final String OUTPUT_BUY_LOTTO_COUNTS = "%d개를 구매했습니다.\n";

    private OutputView() {
    }

    public static void outputBuyLottoCounts(final int lottoCounts) {
        System.out.printf(OUTPUT_BUY_LOTTO_COUNTS, lottoCounts);
    }

    public static void outputLottos(final List<Lotto> lottos) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Lotto lotto : lottos) {
            List<String> numbersString = lotto.getLottoNumbers()
                    .stream()
                    .map(numbers -> String.valueOf(numbers))
                    .collect(Collectors.toList());

            stringBuilder.append("[")
                    .append(String.join( ", ", numbersString))
                    .append("]\n");
        }

        System.out.println(stringBuilder);
    }
}
