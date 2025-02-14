package lotto.dto.response;

import java.math.BigDecimal;
import lotto.domain.Rank;

import java.util.Map;

public record ResultResponse(
        Map<Rank, Long> rankCount,
        BigDecimal rateOfReturn
) {
    public static ResultResponse of(Map<Rank, Long> rankCount, BigDecimal rateOfReturn) {
        return new ResultResponse(rankCount, rateOfReturn);
    }
}
