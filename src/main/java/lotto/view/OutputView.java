package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;

public class OutputView {

    private static final String OUTPUT_BUY_LOTTO_COUNTS = "%d개를 구매했습니다.\n";
    private static final String OUTPUT_LOTTO_INFO_PREFIX = "[";
    private static final String OUTPUT_LOTTO_INFO_SUFFIX = "]\n";
    private static final String OUTPUT_LOTTO_INFO_DELIMITER = ", ";

    private OutputView() {
    }

    public static void outputBuyLottoCounts(final int lottoCounts) {
        System.out.printf(OUTPUT_BUY_LOTTO_COUNTS, lottoCounts);
    }

    public static void outputLottos(final List<Lotto> lottos) {
        StringBuilder stringBuilder = new StringBuilder();
        lottos.forEach(lotto -> stringBuilder.append(getLottoInfosFormat(lotto)));
        System.out.println(stringBuilder);
    }

    private static String getLottoInfosFormat(Lotto lotto) {
        return OUTPUT_LOTTO_INFO_PREFIX + getLottoInfos(lotto) + OUTPUT_LOTTO_INFO_SUFFIX;
    }

    private static String getLottoInfos(Lotto lotto) {
        return lotto.getLottoNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(OUTPUT_LOTTO_INFO_DELIMITER));
    }
}
