package lotto.domain.dto;

public class ResultDTO {
    private int round;
    private String name;
    private int first;
    private int second;
    private int third;
    private int fourth;
    private int fifth;
    private int miss;
    private long totalWinningMoney;
    private double earningRate;

    public static class Builder {
        private int round;
        private String name;

        private int first = 0;
        private int second = 0;
        private int third = 0;
        private int fourth = 0;
        private int fifth = 0;
        private int miss = 0;
        private long totalWinningMoney = 0;
        private double earningRate = 0;

        public Builder(int round, String name) {
            this.round = round;
            this.name = name;
        }

        public Builder first(int value) {
            this.first = value;
            return this;
        }

        public Builder second(int value) {
            this.second = value;
            return this;
        }

        public Builder third(int value) {
            this.third = value;
            return this;
        }

        public Builder fourth(int value) {
            this.fourth = value;
            return this;
        }

        public Builder fifth(int value) {
            this.fifth = value;
            return this;
        }

        public Builder miss(int value) {
            this.miss = value;
            return this;
        }

        public Builder totalWinningMoney(int value) {
            this.totalWinningMoney = value;
            return this;
        }

        public Builder earningRate(int value) {
            this.earningRate = value;
            return this;
        }

        public ResultDTO build() {
            return new ResultDTO(this);
        }
    }

    private ResultDTO(Builder builder) {
        this.round = builder.round;
        this.name = builder.name;
        this.first = builder.first;
        this.second = builder.second;
        this.third = builder.third;
        this.fourth = builder.fourth;
        this.fifth = builder.fifth;
        this.miss = builder.miss;
        this.totalWinningMoney = builder.totalWinningMoney;
        this.earningRate = builder.earningRate;
    }
}
