package lotto.domain.lottoresult.dto;

public class LottoStatisticsDTO {
    private int countsOfFirstRank;
    private int countsOfSecondRank;
    private int countsOfThirdRank;
    private int countsOfFourthRank;
    private int countsOfFifthRank;
    private double profitRatio;

    public int getCountsOfFirstRank() {
        return countsOfFirstRank;
    }

    public void setCountsOfFirstRank(int countsOfFirstRank) {
        this.countsOfFirstRank = countsOfFirstRank;
    }

    public int getCountsOfSecondRank() {
        return countsOfSecondRank;
    }

    public void setCountsOfSecondRank(int countsOfSecondRank) {
        this.countsOfSecondRank = countsOfSecondRank;
    }

    public int getCountsOfThirdRank() {
        return countsOfThirdRank;
    }

    public void setCountsOfThirdRank(int countsOfThirdRank) {
        this.countsOfThirdRank = countsOfThirdRank;
    }

    public int getCountsOfFourthRank() {
        return countsOfFourthRank;
    }

    public void setCountsOfFourthRank(int countsOfFourthRank) {
        this.countsOfFourthRank = countsOfFourthRank;
    }

    public int getCountsOfFifthRank() {
        return countsOfFifthRank;
    }

    public void setCountsOfFifthRank(int countsOfFifthRank) {
        this.countsOfFifthRank = countsOfFifthRank;
    }

    public double getProfitRatio() {
        return profitRatio;
    }

    public void setProfitRatio(double profitRatio) {
        this.profitRatio = profitRatio;
    }
}
