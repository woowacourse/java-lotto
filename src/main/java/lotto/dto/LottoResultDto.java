package lotto.dto;

import java.util.EnumMap;
import lotto.domain.LottoStatistics;
import lotto.domain.Rank;

public class LottoResultDto {

    private final EnumMap<Rank, Integer> statistics;
    private final double yield;

    public LottoResultDto(EnumMap<Rank, Integer> statistics, double yield) {
        this.statistics = statistics;
        this.yield = yield;
    }

    public static LottoResultDto from(LottoStatistics lottoStatistics, double yield) {
        return new LottoResultDto(lottoStatistics.getStatistics(), yield);
    }


    public EnumMap<Rank, Integer> getStatistics() {
        return new EnumMap<>(statistics);
    }

    public double getYield() {
        return yield;
    }
}
