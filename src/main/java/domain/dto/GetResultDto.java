package domain.dto;

import domain.Rank;
import java.util.EnumMap;

public record GetResultDto(
        EnumMap<Rank, Integer> countRank,
        double profit
) {
}
