package dto;

import domain.Rank;

import java.util.ArrayList;
import java.util.List;

public class RanksDto {
    private final List<RankDto> rankDtos;

    public RanksDto(List<RankDto> rankDtos) {
        this.rankDtos = rankDtos;
    }

//    public static RanksDto from(Rank ranks){
//        List<RankDto> rankDtos = new ArrayList<>();
//        for (Rank rank : ranks.values()) {
//            rankDtos.add(RankDto.from(rank));
//        }
//        return new RanksDto(rankDtos);
//    }
}
