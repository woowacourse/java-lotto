package domain.dto;

import domain.Rank;
import java.util.EnumMap;

public record ResultDto(
        EnumMap<Rank, Integer> countRank,
        double profit
) {
}
