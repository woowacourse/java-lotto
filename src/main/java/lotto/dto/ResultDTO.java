package lotto.dto;

import java.util.List;

public class ResultDTO {
    private int round;
    private List<Integer> ranks;
    private double winningRate;

    public int getRound() {
        return round;
    }

    public void setRound(final int round) {
        this.round = round;
    }

    public List<Integer> getRanks() {
        return ranks;
    }

    public void setRanks(final List<Integer> ranks) {
        this.ranks = ranks;
    }

    public double getWinningRate() {
        return winningRate;
    }

    public void setWinningRate(final double winningRate) {
        this.winningRate = winningRate;
    }


}
