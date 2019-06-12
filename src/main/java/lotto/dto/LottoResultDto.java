package lotto.dto;

import lotto.domain.Rank;

import java.math.BigDecimal;
import java.util.Map;

public class LottoResultDto {
    private final Map<Rank, Integer> results;
    private final BigDecimal summury;

    public LottoResultDto(Map<Rank, Integer> results, BigDecimal summury) {
        this.results = results;
        this.summury = summury;
    }

    public Map<Rank, Integer> getResults() {
        return results;
    }

    public BigDecimal getSummury() {
        return summury;
    }
}
