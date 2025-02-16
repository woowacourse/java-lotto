package dto.response;

import domain.Rank;
import java.util.Map;

public record WinningResultResponse(
        Map<Rank, Integer> rankCount,
        double yield
) {
    public static WinningResultResponse of(Map<Rank, Integer> rankCount, double yield) {
        return new WinningResultResponse(rankCount, yield);
    }
}
