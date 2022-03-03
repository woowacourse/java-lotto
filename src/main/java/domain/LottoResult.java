package domain;

import static domain.Lotto.LOTTO_PRICE;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingLong;

import java.util.EnumMap;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class LottoResult {
    private final Map<Rank, LongSummaryStatistics> lottoResult;
    private final double rateOfReturn;

    private LottoResult(Builder builder) {
        WinningLotto winningLotto = builder.getWinningLotto();
        Lottos lottos = builder.getLottos();

        this.lottoResult = Map.copyOf(getLottoResultGroupByRank(winningLotto, lottos));
        this.rateOfReturn = getRateOfReturn(lottos, getTotalReturnByLottoResult(lottoResult));
    }

    private Map<Rank, LongSummaryStatistics> getLottoResultGroupByRank(WinningLotto winningLotto,
                                                                       Lottos lottos) {
        return lottos.getLottos()
                .stream()
                .map(winningLotto::getRankByLotto)
                .collect(groupingBy(Function.identity(), () -> new EnumMap<>(Rank.class),
                        summarizingLong(Rank::getPrize)));
    }

    private long getTotalReturnByLottoResult(Map<Rank, LongSummaryStatistics> lottoResult) {
        return lottoResult.values()
                .stream()
                .mapToLong(LongSummaryStatistics::getSum)
                .sum();
    }

    public LongSummaryStatistics getLottoResultByRank(Rank rank) {
        return lottoResult.getOrDefault(rank, new LongSummaryStatistics());
    }

    private double getRateOfReturn(Lottos lottos, long totalReturn) {
        return (double) totalReturn / (lottos.getLottos().size() * LOTTO_PRICE);
    }

    public double getRateOfReturn() {
        return rateOfReturn;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private static final String ERROR_MESSAGE_FOR_NULL_WINNING_LOTTO = "당첨 정보가 비어있습니다.";
        private static final String ERROR_MESSAGE_FOR_NULL_LOTTOS = "로또가 비어있습니다.";

        private WinningLotto winningLotto;
        private Lottos lottos;

        public Builder winningLotto(WinningLotto winningLotto) {
            Objects.requireNonNull(winningLotto, ERROR_MESSAGE_FOR_NULL_WINNING_LOTTO);
            this.winningLotto = winningLotto;
            return this;
        }

        public Builder lottos(Lottos lottos) {
            Objects.requireNonNull(lottos, ERROR_MESSAGE_FOR_NULL_LOTTOS);
            this.lottos = lottos;
            return this;
        }

        public LottoResult build() {
            return new LottoResult(this);
        }

        public WinningLotto getWinningLotto() {
            return winningLotto;
        }

        public Lottos getLottos() {
            return lottos;
        }
    }
}
