package controller.dto;

import domain.Statistic;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticDto {
    private final Map<RankDto, Integer> statisticDto;
    private final Statistic statistic;

    public StatisticDto(Map<RankDto, Integer> statisticDto, Statistic statistic) {
        this.statisticDto = new LinkedHashMap<>(statisticDto);
        this.statistic = statistic;
    }

    public static StatisticDto from(Statistic statistics) {
        return statistics.getStatistics().entrySet()
                .stream()
                .collect(Collectors.collectingAndThen(Collectors.toMap(
                                statistic -> RankDto.from(statistic.getKey()),
                                Map.Entry::getValue,
                                (key, value) -> key,
                                LinkedHashMap::new
                        ), o -> new StatisticDto(o, statistics))
                );
    }

    public Map<RankDto, Integer> getStatisticDto() {
        return new LinkedHashMap<>(statisticDto);
    }

    public Statistic getStatistic() {
        return statistic;
    }
}
