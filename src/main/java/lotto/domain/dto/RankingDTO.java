package lotto.domain.dto;

public class RankingDTO implements Comparable<RankingDTO> {
    private String name;
    private long totalWinningMoney;

    public RankingDTO(String name, long totalWinningMoney) {
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
    public int compareTo(RankingDTO rankingDTO) {
        return Long.compare(totalWinningMoney, rankingDTO.totalWinningMoney);
    }

    @Override
    public String toString() {
        return "RankingDTO{" +
                "name='" + name + '\'' +
                ", totalWinningMoney=" + totalWinningMoney +
                '}';
    }
}
