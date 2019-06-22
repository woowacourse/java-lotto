package lotto.dto;

import lotto.domain.Rank;
import lotto.domain.Statistics;

import java.util.Map;

public class StatisticsDto {
    private String round;
    private String firstCount;
    private String secondCount;
    private String thirdCount;
    private String fourthCount;
    private String fifthCount;
    private String missCount;
    private String returnOfRate;

    public StatisticsDto(String round, String firstCount, String secondCount, String thirdCount, String fourthCount, String fifthCount, String missCount, String returnOfRate) {
        this.round = round;
        this.firstCount = firstCount;
        this.secondCount = secondCount;
        this.thirdCount = thirdCount;
        this.fourthCount = fourthCount;
        this.fifthCount = fifthCount;
        this.missCount = missCount;
        this.returnOfRate = returnOfRate;
    }

    public StatisticsDto(String round, Statistics statistics) {
        this.round = round;

        Map<Rank, Long> result = statistics.ranksStatistics();
        firstCount = String.valueOf(result.get(Rank.FIRST));
        secondCount = String.valueOf(result.get(Rank.SECOND));
        thirdCount = String.valueOf(result.get(Rank.THIRD));
        fourthCount = String.valueOf(result.get(Rank.FOURTH));
        fifthCount = String.valueOf(result.get(Rank.FIFTH));
        missCount = String.valueOf(result.get(Rank.MISS));

        String percentFormat = "%d%%";
        int percent = 100;

        returnOfRate = String.format(percentFormat, ((int) (statistics.returnOfRate() * percent)));
    }


    public String getRound() {
        return round;
    }

    public String getFirstCount() {
        return firstCount;
    }

    public String getSecondCount() {
        return secondCount;
    }

    public String getThirdCount() {
        return thirdCount;
    }

    public String getFourthCount() {
        return fourthCount;
    }

    public String getFifthCount() {
        return fifthCount;
    }

    public String getMissCount() {
        return missCount;
    }

    public String getReturnOfRate() {
        return returnOfRate;
    }
}
