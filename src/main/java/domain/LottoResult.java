package domain;

public class LottoResult {
    private final LottoWinningTable winningTable;
    private final double earningRate;

    public LottoResult(LottoWinningTable winningTable, double earningRate) {
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
