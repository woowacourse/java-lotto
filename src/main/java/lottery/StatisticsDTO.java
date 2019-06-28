package lottery;

import java.util.List;

public class StatisticsDTO {
    private List<CountsOfRankDTO> lotteryResults;
    private double earningRates;

    public List<CountsOfRankDTO> getCountsOfRanks() {
        return lotteryResults;
    }

    public void setLotteryResults(List<CountsOfRankDTO> lotteryResults) {
        this.lotteryResults = lotteryResults;
    }

    public double getEarningRates() {
        return earningRates;
    }

    public void setEarningRates(double earningRates) {
        this.earningRates = earningRates;
    }
}
