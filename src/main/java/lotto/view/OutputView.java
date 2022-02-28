package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

public class OutputView {

    private static final String OUTPUT_BUY_LOTTO_COUNTS = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String OUTPUT_LOTTO_INFO_PREFIX = "[";
    private static final String OUTPUT_LOTTO_INFO_SUFFIX = "]\n";
    private static final String OUTPUT_LOTTO_INFO_DELIMITER = ", ";
    private static final String OUTPUT_RESULT_INFO_MESSAGE = "당첨 통계";
    private static final String OUTPUT_RESULT_DELIMITER = "---------";
    private static final String OUTPUT_RANK_RESULT_SECOND = "%d개 일치, 보너스 볼 일치(%d원)- %d개\n";
    private static final String OUTPUT_RANK_RESULT = "%d개 일치 (%d원)- %d개\n";
    private static final String OUTPUT_LOTTO_RESULT_YIELD = "총 수익률은 %.2f 입니다.\n";

    private OutputView() {
    }

    public static void outputBuyLottoCounts(final int manualCounts, final int autoCounts) {
        System.out.printf(OUTPUT_BUY_LOTTO_COUNTS, manualCounts, autoCounts);
    }

    public static void outputLottos(final List<Lotto> lottos) {
        StringBuilder stringBuilder = new StringBuilder();
        lottos.forEach(lotto -> stringBuilder.append(getLottoInfosFormat(lotto)));
        System.out.println(stringBuilder);
    }

    private static String getLottoInfosFormat(final Lotto lotto) {
        return OUTPUT_LOTTO_INFO_PREFIX + getLottoInfos(lotto) + OUTPUT_LOTTO_INFO_SUFFIX;
    }

    private static String getLottoInfos(final Lotto lotto) {
        return lotto.getLottoNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(OUTPUT_LOTTO_INFO_DELIMITER));
    }

    public static void outputResult(final LottoResult lottoResult) {
        System.out.println(OUTPUT_RESULT_INFO_MESSAGE);
        System.out.println(OUTPUT_RESULT_DELIMITER);

        printRankResults(lottoResult);
        printYield(lottoResult.calculateYield());
    }

    private static void printRankResults(final LottoResult lottoResult) {
        lottoResult.getRankResults().entrySet().stream()
                .filter(rank -> !rank.getKey().isNothing())
                .forEach(rank -> printRankResult(rank.getKey(), rank.getValue()));
    }

    private static void printRankResult(final Rank rank, final int count) {
        if (rank == Rank.SECOND) {
            System.out.printf(OUTPUT_RANK_RESULT_SECOND, rank.getHitCounts(), rank.getReward(), count);
            return;
        }
        System.out.printf(OUTPUT_RANK_RESULT, rank.getHitCounts(), rank.getReward(), count);
    }

    private static void printYield(final double yield) {
        System.out.printf(OUTPUT_LOTTO_RESULT_YIELD, yield);
    }
}
