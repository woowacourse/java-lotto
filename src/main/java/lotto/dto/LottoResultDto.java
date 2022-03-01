package lotto.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.LottoStatistics;
import lotto.domain.Rank;

public class LottoResultDto {

    private final Map<RankDto, Integer> statistics;
    private final double yield;

    public LottoResultDto(Map<RankDto, Integer> statistics, double yield) {
        this.statistics = statistics;
        this.yield = yield;
    }

    public static LottoResultDto from(LottoStatistics lottoStatistics, double yield) {
        return new LottoResultDto(createStatistics(lottoStatistics), yield);
    }

    private static Map<RankDto, Integer> createStatistics(LottoStatistics lottoStatistics) {
        return lottoStatistics.getStatistics().entrySet().stream()
                .filter(entry -> isNotMiss(entry.getKey()))
                .collect(Collectors.toMap(
                        entry -> RankDto.from(entry.getKey()),
                        Map.Entry::getValue,
                        (key, value) -> key,
                        LinkedHashMap::new
                ));
    }

    private static boolean isNotMiss(Rank rank) {
        return rank.getMatchCount() != Rank.MISS.getMatchCount();
    }

    public Map<RankDto, Integer> getStatistics() {
        return new LinkedHashMap<>(statistics);
    }

    public double getYield() {
        return yield;
    }
}