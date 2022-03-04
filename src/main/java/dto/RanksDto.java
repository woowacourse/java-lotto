package dto;

import domain.HitResult;
import domain.Rank;

import java.util.ArrayList;
import java.util.List;

public class RanksDto {

    private final List<RankDto> rankDtos;
    private double incomeRate;

    public RanksDto(double incomeRate, HitResult hitResult) {
        this.rankDtos = makeRankDtos(hitResult);
        this.incomeRate = incomeRate;
    }

    private List<RankDto> makeRankDtos(HitResult hitResult) {
        List<RankDto> rankDtos = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            rankDtos.add(RankDto.from(rank, hitResult));
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
