package lotto.dto;

import java.util.Map;
import lotto.domain.Profit;
import lotto.domain.Rank;

public record ProfitDto(Map<Rank, Integer> rankCounts) {

    public static ProfitDto from(Profit profit) {
        return new ProfitDto(profit.getRankCounts());
    }
}
