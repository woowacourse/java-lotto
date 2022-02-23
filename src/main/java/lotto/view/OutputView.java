package lotto.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

public class OutputView {

    private static final String OUTPUT_BUY_LOTTO_COUNTS = "%d개를 구매했습니다.\n";
    private static final String OUTPUT_LOTTO_INFO_PREFIX = "[";
    private static final String OUTPUT_LOTTO_INFO_SUFFIX = "]\n";
    private static final String OUTPUT_LOTTO_INFO_DELIMITER = ", ";
    private static final String OUTPUT_RESULT_INFO_MESSAGE = "당첨 통계";
    private static final String OUTPUT_RESULT_DELIMITER = "---------";

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

    private static String getLottoInfosFormat(final Lotto lotto) {
        return OUTPUT_LOTTO_INFO_PREFIX + getLottoInfos(lotto) + OUTPUT_LOTTO_INFO_SUFFIX;
    }

    private static String getLottoInfos(Lotto lotto) {
        return lotto.getLottoNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(OUTPUT_LOTTO_INFO_DELIMITER));
    }

    public static void outputResult(final LottoResult lottoResult) {
        System.out.println(OUTPUT_RESULT_INFO_MESSAGE);
        System.out.println(OUTPUT_RESULT_DELIMITER);

        Map<Rank, Integer> rankResults = lottoResult.getRankResults();
        StringBuilder stringBuilder = new StringBuilder();
        for (Rank rank : rankResults.keySet()) {
            stringBuilder.append(
                    rank.getHitCounts() + "개 일치 (" + rank.getReward() + "원) - " + rankResults.get(rank) + "개\n");
        }

        stringBuilder.append("총 수익률은 " + lottoResult.calculateYield() + "입니다.");
        System.out.println(stringBuilder);
    }
}
