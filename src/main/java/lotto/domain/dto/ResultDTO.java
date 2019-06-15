package lotto.domain.dto;

import lotto.domain.lotto.Rank;

import java.util.EnumMap;
import java.util.Map;

import static lotto.domain.lotto.Rank.*;

public class ResultDTO {
    private int round;
    private String name;
    private Map<Rank, Long> lottoScore;
    private long totalWinningMoney;
    private int payment;
    private double earningRate;

    public static class Builder {
        private int round;
        private String name;

        private Map<Rank, Long> lottoScore = new EnumMap<>(Rank.class);
        private long totalWinningMoney = 0;
        private int payment = 0;
        private double earningRate = 0;

        public Builder(int round, String name) {
            this.round = round;
            this.name = name;
        }

        public Builder first(long value) {
            lottoScore.put(FIRST, value);
            return this;
        }

        public Builder second(long value) {
            lottoScore.put(SECOND, value);
            return this;
        }

        public Builder third(long value) {
            lottoScore.put(THIRD, value);
            return this;
        }

        public Builder fourth(long value) {
            lottoScore.put(FOURTH, value);
            return this;
        }

        public Builder fifth(long value) {
            lottoScore.put(FIFTH, value);
            return this;
        }

        public Builder miss(long value) {
            lottoScore.put(MISS, value);
            return this;
        }

        public Builder payment(int value) {
            this.payment = value;
            return this;
        }

        public ResultDTO build() {
            return new ResultDTO(this);
        }
    }

    private ResultDTO(Builder builder) {
        this.round = builder.round;
        this.name = builder.name;
        this.lottoScore = new EnumMap<>(builder.lottoScore);
        this.totalWinningMoney = builder.totalWinningMoney;
        this.payment = builder.payment;
        this.earningRate = builder.earningRate;
    }

    public int getRound() {
        return round;
    }

    public String getName() {
        return name;
    }

    public Map<Rank, Long> getLottoScore() {
        return lottoScore;
    }

    public long getTotalWinningMoney() {
        return totalWinningMoney;
    }

    public int getPayment() {
        return payment;
    }

    public double getEarningRate() {
        return earningRate;
    }

    public void setTotalWinningMoney(long totalWinningMoney) {
        this.totalWinningMoney = totalWinningMoney;
    }

    public void setEarningRate(double earningRate) {
        this.earningRate = earningRate;
    }

    public long get(Rank rank) {
        return lottoScore.get(rank);
    }
}
