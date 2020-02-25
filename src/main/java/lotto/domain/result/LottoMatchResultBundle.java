package lotto.domain.result;

import lotto.domain.result.rank.Rank;
import lotto.util.NullOrEmptyValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LottoMatchResultBundle {
    private final List<LottoMatchResult> lottoMatchResults;

    public LottoMatchResultBundle(List<LottoMatchResult> lottoMatchResults) {
        NullOrEmptyValidator.isNullOrEmpty(lottoMatchResults);

        this.lottoMatchResults = lottoMatchResults;
    }

    public OverallResult createOverallResult() {
        Map<Rank, Integer> overallResult = initializeOverallWinResult();

        overallResult.replaceAll((key, value) -> countMatchTickets(key));

        return new OverallResult(overallResult);
    }

    private Map<Rank, Integer> initializeOverallWinResult() {
        Map<Rank, Integer> overallWinResult = new HashMap<>();
        for (Rank rank : Rank.values()) {
            overallWinResult.put(rank, 0);
        }
        return overallWinResult;
    }

    private int countMatchTickets(Rank rank) {
        return (int) lottoMatchResults.stream()
                .filter(lottoResult -> lottoResult.has(rank))
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoMatchResultBundle that = (LottoMatchResultBundle) o;
        return Objects.equals(lottoMatchResults, that.lottoMatchResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoMatchResults);
    }

    @Override
    public String toString() {
        return "LottoResultBundle{" +
                "lottoResults=" + lottoMatchResults +
                '}';
    }
}
