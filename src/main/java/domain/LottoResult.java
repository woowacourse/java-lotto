package domain;

import static domain.Lotto.LOTTO_PRICE;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingLong;

import java.util.EnumMap;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
    private static final String ERROR_MESSAGE_FOR_NULL_WINNING_LOTTO = "당첨 정보가 비어있습니다.";
    private static final String ERROR_MESSAGE_FOR_NULL_LOTTOS = "로또가 비어있습니다.";

    private final Map<Rank, LongSummaryStatistics> lottoResult;
    private final double rateOfReturn;

    private LottoResult(Map<Rank, LongSummaryStatistics> lottoResult, double rateOfReturn) {
        this.lottoResult = lottoResult;
        this.rateOfReturn = rateOfReturn;
    }

    public static LottoResult of(WinningLotto winningLotto, Lottos lottos) {
        Map<Rank, LongSummaryStatistics> lottoResult = Map.copyOf(getLottoResult(winningLotto, lottos));
        double rateOfReturn = getRateOfReturn(lottos, getTotalReturnByLottoResult(lottoResult));

        return new LottoResult(lottoResult, rateOfReturn);
    }

    private static Map<Rank, LongSummaryStatistics> getLottoResult(WinningLotto winningLotto,
                                                                   Lottos lottos) {
        Objects.requireNonNull(winningLotto, ERROR_MESSAGE_FOR_NULL_WINNING_LOTTO);
        Objects.requireNonNull(lottos, ERROR_MESSAGE_FOR_NULL_LOTTOS);

        return lottos.getLottos()
                .stream()
                .map(winningLotto::getRankByLotto)
                .collect(groupingBy(identity(),
                        () -> new EnumMap<>(Rank.class),
                        summarizingLong(Rank::getPrize)));
    }

    private static long getTotalReturnByLottoResult(Map<Rank, LongSummaryStatistics> lottoResult) {
        return lottoResult.values()
                .stream()
                .mapToLong(LongSummaryStatistics::getSum)
                .sum();
    }

    private static double getRateOfReturn(Lottos lottos, long totalReturn) {
        return (double) totalReturn / (lottos.getLottos().size() * LOTTO_PRICE);
    }

    public LongSummaryStatistics getLottoResultByRank(Rank rank) {
        return lottoResult.getOrDefault(rank, new LongSummaryStatistics());
    }

    public double getRateOfReturn() {
        return rateOfReturn;
    }
}
