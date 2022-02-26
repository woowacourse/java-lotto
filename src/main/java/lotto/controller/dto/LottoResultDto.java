package lotto.controller.dto;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.domain.Rank;

public class LottoResultDto {

    private final List<RankDto> ranks;
    private final double yield;

    public LottoResultDto(Map<Rank, Long> ranks, double yield) {
        this.ranks = Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NOTHING)
                .map(rank -> new RankDto(rank, ranks.getOrDefault(rank, 0L)))
                .collect(toList());
        this.yield = yield;
    }

    public List<RankDto> getRanks() {
        return ranks;
    }

    public double getYield() {
        return yield;
    }
}
