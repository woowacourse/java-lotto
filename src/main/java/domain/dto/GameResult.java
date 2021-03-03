package domain.dto;

import domain.lottoGame.LottoWinningTable;

public class GameResult {
    private final LottoWinningTable winningTable;
    private final double earningRate;

    public GameResult(LottoWinningTable winningTable, double earningRate) {
        this.winningTable = winningTable;
        this.earningRate = earningRate;
    }

    public LottoWinningTable getWinningTable() {
        return winningTable;
    }

    public double getEarningRate() {
        return earningRate;
    }
}
