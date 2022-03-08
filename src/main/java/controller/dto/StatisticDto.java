package controller.dto;

import domain.Statistic;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticDto {
    private final Map<RankDto, Integer> statisticDto;
    private final double profitRate;

    public StatisticDto(Map<RankDto, Integer> statisticDto, double profitRate) {
        this.statisticDto = new LinkedHashMap<>(statisticDto);
        this.profitRate = profitRate;
    }

    public static StatisticDto from(Statistic statistics, double profitRate) {
        return statistics.getStatistics().entrySet()
                .stream()
                .collect(Collectors.collectingAndThen(Collectors.toMap(
                                statistic -> RankDto.from(statistic.getKey()),
                                Map.Entry::getValue,
                                (key, value) -> key,
                                LinkedHashMap::new
                        ), statisticDto1 -> new StatisticDto(statisticDto1, profitRate))
                );
    }

    public Map<RankDto, Integer> getStatisticDto() {
        return new LinkedHashMap<>(statisticDto);
    }

    public double getProfitRate() {
        return profitRate;
    }
}
