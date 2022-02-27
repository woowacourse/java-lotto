package lotto.controller.dto;

import static java.util.stream.Collectors.*;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.domain.Rank;

public class LottoResultDto {

    private static final long RANK_AMOUNT_DEFAULT_VALUE = 0L;

    private final List<RankDto> ranks;
    private final double yield;

    private LottoResultDto(Map<Rank, Long> ranks, double yield) {
        this.ranks = Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NOTHING)
                .map(rank -> RankDto.from(rank, ranks.getOrDefault(rank, RANK_AMOUNT_DEFAULT_VALUE)))
                .collect(toList());

        this.yield = yield;
    }

    public static LottoResultDto from(Map<Rank, Long> ranks, double yield) {
        return new LottoResultDto(ranks, yield);
    }

    public List<RankDto> getRanks() {
        return ranks;
    }

    public double getYield() {
        return yield;
    }
}
