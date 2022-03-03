package dto;

import domain.Rank;
import domain.ResultStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RanksDto {

    private final List<RankDto> rankDtos;
    private final double incomeRate;

    private RanksDto(List<RankDto> rankDtos, double incomeRate) {
        this.rankDtos = new ArrayList<>(rankDtos);
        this.incomeRate = incomeRate;
    }

    public static RanksDto from(ResultStatus resultStatus, double incomeRate) {
        List<RankDto> rankDtos = new ArrayList<>();
        Map<Rank, Integer> resultStatistics = resultStatus.getResultStatistics();
        for (Rank rank : resultStatistics.keySet()) {
            rankDtos.add(RankDto.from(rank, resultStatistics.get(rank)));

        }
        return new RanksDto(rankDtos, incomeRate);
    }

    public List<RankDto> getRankDtos() {
        return Collections.unmodifiableList(rankDtos);
    }

    public double getIncomeRate() {
        return incomeRate;
    }
}