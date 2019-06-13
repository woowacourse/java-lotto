package lotto.dto;

import java.math.BigInteger;

public class ResultDto {
    private int round;
    private int totalPurchaseMoney;
    private BigInteger totalWinningMoney;

    public ResultDto(int round, int totalPurchaseMoney, BigInteger totalWinningMoney) {
        this.round = round;
        this.totalPurchaseMoney = totalPurchaseMoney;
        this.totalWinningMoney = totalWinningMoney;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getTotalPurchaseMoney() {
        return totalPurchaseMoney;
    }

    public void setTotalPurchaseMoney(int totalPurchaseMoney) {
        this.totalPurchaseMoney = totalPurchaseMoney;
    }

    public BigInteger getTotalWinningMoney() {
        return totalWinningMoney;
    }

    public void setTotalWinningMoney(BigInteger totalWinningMoney) {
        this.totalWinningMoney = totalWinningMoney;
    }
}