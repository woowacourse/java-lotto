package dto;

import domain.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RanksDto {

    public static final int RANK_NUMBER = 5;

    private final List<RankDto> rankDtos;
    private final double incomeRate;

    private RanksDto(List<RankDto> rankDtos, double incomeRate) {
        this.rankDtos = new ArrayList<>(rankDtos);
        this.incomeRate = incomeRate;
    }

    public static RanksDto from(double incomeRate) {
        List<RankDto> rankDtos = new ArrayList<>();
        int rankNumber = RANK_NUMBER;

        for (Rank rank : Rank.values()) {
            rankDtos.add(RankDto.from(rank, rankNumber--));
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
