package controller.dto;

import domain.Statistic;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticDto {
    private final Map<RankDto, Integer> statistic;

    public StatisticDto(Map<RankDto, Integer> statistic) {
        this.statistic = new LinkedHashMap<>(statistic);
    }

    public static StatisticDto from(Statistic statistics) {
        Map<RankDto, Integer> collect = statistics.getStatistics().entrySet()
                .stream()
                .collect(Collectors.toMap(
                        statistic -> RankDto.from(statistic.getKey()),
                        Map.Entry::getValue,
                        (key, value) -> key,
                        LinkedHashMap::new));
        return new StatisticDto(collect);
    }

    public Map<RankDto, Integer> getStatistic() {
        return new LinkedHashMap<>(statistic);
    }

}
