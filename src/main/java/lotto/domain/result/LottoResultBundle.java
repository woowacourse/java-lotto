package lotto.domain.result;

import lotto.domain.result.rank.Rank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LottoResultBundle {
    private final List<LottoResult> lottoResults;

    public LottoResultBundle(List<LottoResult> lottoResults) {
        this.lottoResults = lottoResults;
    }

    public Map<Rank, Integer> computeOverallWinResult() {
        Map<Rank, Integer> overallResult = initializeOverallWinResult();

        overallResult.replaceAll((key, value) -> countMatchTickets(key));

        return overallResult;
    }

    private Map<Rank, Integer> initializeOverallWinResult() {
        Map<Rank, Integer> overallWinResult = new HashMap<>();
        for (Rank rank : Rank.values()) {
            overallWinResult.put(rank, 0);
        }
        return overallWinResult;
    }

    private int countMatchTickets(Rank rank) {
        return (int) lottoResults.stream()
                .filter(lottoResult -> lottoResult.has(rank))
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResultBundle that = (LottoResultBundle) o;
        return Objects.equals(lottoResults, that.lottoResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoResults);
    }

    @Override
    public String toString() {
        return "LottoResultBundle{" +
                "lottoResults=" + lottoResults +
                '}';
    }
}
