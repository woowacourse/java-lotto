package dto;

import java.util.EnumMap;
import model.Rank;

public record LottoResultDto(
        EnumMap<Rank, Integer> rankResult,
        double profitRate
) {
}
