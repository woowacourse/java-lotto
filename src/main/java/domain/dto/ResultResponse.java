package domain.dto;

import domain.Rank;
import java.util.EnumMap;

public record ResultResponse(
        EnumMap<Rank, Integer> countRank,
        double profit
) {
}
