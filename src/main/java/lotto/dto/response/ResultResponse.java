package lotto.dto.response;

import lotto.domain.Rank;

import java.util.Map;

public record ResultResponse(
    Map<Rank, Integer> rankCount,
    double rateOfReturn
) {
    public static ResultResponse of(Map<Rank, Integer> rankCount, double rateOfReturn) {
        return new ResultResponse(rankCount, rateOfReturn);
    }
}
