package lotto.domain.dto;

import lotto.domain.lotto.Rank;
import lotto.domain.lotto.Result;

import java.util.EnumMap;
import java.util.Map;

import static lotto.domain.lotto.Rank.*;

public class ResultDTO {
    private int round;
    private String name;
    private Result result;
    private long totalWinningMoney;
    private double earningRate;

    public static class Builder {
        private int round;
        private String name;

        private Map<Rank, Long> lottoScore = new EnumMap<>(Rank.class);
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

        public ResultDTO build() {
            return new ResultDTO(this);
        }
    }

    private ResultDTO(Builder builder) {
        this.round = builder.round;
        this.name = builder.name;
        this.result = new Result(builder.lottoScore);
        this.totalWinningMoney = this.result.calculateTotalWinningMoney();
        this.earningRate = builder.earningRate;
    }

    public int getRound() {
        return round;
    }

    public String getName() {
        return name;
    }

    public Result getResult() {
        return result;
    }

    public long getTotalWinningMoney() {
        return totalWinningMoney;
    }

    public double getEarningRate() {
        return earningRate;
    }
}
