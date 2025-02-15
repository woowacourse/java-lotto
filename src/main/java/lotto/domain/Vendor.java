package lotto.domain;

import lotto.constant.WinningTier;

import java.util.List;

public class Vendor {

    private final int purchaseAmount;

    public Vendor(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public double calculateProfit(List<WinningTier> winningTiers) {
        int prizeSum = winningTiers.stream().mapToInt(WinningTier::getPrize).sum();
        return (double) prizeSum / this.purchaseAmount;
    }
}
