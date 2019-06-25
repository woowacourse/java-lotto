package lotto.domain.dto;

public class RankingDto implements Comparable<RankingDto> {
    private String name;
    private long totalWinningMoney;

    public RankingDto(String name, long totalWinningMoney) {
        this.name = name;
        this.totalWinningMoney = totalWinningMoney;
    }

    public String getName() {
        return name;
    }

    public long getTotalWinningMoney() {
        return totalWinningMoney;
    }

    @Override
    public int compareTo(RankingDto rankingDTO) {
        return Long.compare(totalWinningMoney, rankingDTO.totalWinningMoney);
    }

    @Override
    public String toString() {
        return "RankingDto{" +
                "name='" + name + '\'' +
                ", totalWinningMoney=" + totalWinningMoney +
                '}';
    }
}
