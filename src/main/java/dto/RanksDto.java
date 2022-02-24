package dto;

<<<<<<< HEAD
<<<<<<< HEAD
import domain.Rank;
<<<<<<< HEAD
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
=======

import java.util.ArrayList;
=======
>>>>>>> 6741479 (feat: 수익률 계산 로직 및 출력 기능 구현)
=======
import domain.Rank;

import java.util.ArrayList;
>>>>>>> 2b5d619 (refactor: 결과 리스트 생성 로직 이동)
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
        int rankNumber = 5;

        for (Rank rank : Rank.values()) {
            rankDtos.add(RankDto.from(rank, rankNumber--));
        }
        return rankDtos;
    }

    public List<RankDto> getRankDtos() {
        return rankDtos;
    }

<<<<<<< HEAD
//    public static RanksDto from(Rank ranks){
//        List<RankDto> rankDtos = new ArrayList<>();
//        for (Rank rank : ranks.values()) {
//            rankDtos.add(RankDto.from(rank));
//        }
//        return new RanksDto(rankDtos);
//    }
>>>>>>> 2821995 (feat: 결과 출력 기능 구현)
=======
    public double getIncomeRate() {
        return incomeRate;
    }
>>>>>>> 6741479 (feat: 수익률 계산 로직 및 출력 기능 구현)
}
