package dto;

import java.util.List;

public class RanksDto {
    private final List<RankDto> rankDtos;
    private double incomeRate;

    public RanksDto(List<RankDto> rankDtos, double incomeRate) {
        this.rankDtos = rankDtos;
        this.incomeRate = incomeRate;
    }

    public List<RankDto> getRankDtos() {
        return rankDtos;
    }

    public double getIncomeRate() {
        return incomeRate;
    }
}
