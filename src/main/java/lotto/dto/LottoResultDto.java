package lotto.dto;

import lotto.domain.LottoResult;
import lotto.domain.core.Rank;

import java.math.BigDecimal;
import java.util.Map;

public class LottoResultDto {
    private final Map<Rank, Integer> results;
    private final BigDecimal summary;

    public LottoResultDto(Map<Rank, Integer> results, BigDecimal summary) {
        this.results = results;
        this.summary = summary;
    }

    public LottoResultDto(LottoResult lottoResult) {
        this(lottoResult.results(), lottoResult.calculate().summary());
    }

    public Map<Rank, Integer> getResults() {
        return results;
    }

    public BigDecimal getSummary() {
        return summary;
    }
}