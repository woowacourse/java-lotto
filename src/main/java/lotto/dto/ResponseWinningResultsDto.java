package lotto.dto;

import java.util.Map;
import lotto.domain.LottoPrize;

public class ResponseWinningResultsDto {

    private Map<LottoPrize, Integer> results;

    public ResponseWinningResultsDto(Map<LottoPrize, Integer> results) {
        this.results = results;
    }

    public Map<LottoPrize, Integer> getResults() {
        return results;
    }
}
