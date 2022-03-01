package dto;

import domain.Rank;

import java.util.ArrayList;
import java.util.List;

public class RanksDto {

    private final List<RankDto> rankDtos;
    private double incomeRate;

    public RanksDto(double incomeRate) {
        this.rankDtos = makeRankDtos();
        this.incomeRate = incomeRate;
    }

    private List<RankDto> makeRankDtos() {
        List<RankDto> rankDtos = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            rankDtos.add(RankDto.from(rank));
        }
        return rankDtos;
    }

    public List<RankDto> getRankDtos() {
        return rankDtos;
    }

    public double getIncomeRate() {
        return incomeRate;
    }
}
