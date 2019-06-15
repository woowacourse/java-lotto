package lotto.db.dto;

import java.util.*;

public class LottoGameResultDTO {
    private int winningLottoId;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;
    private int bonusBall;
    private int money;

    public static class Builder {
        private int num1;
        private int num2;
        private int num3;
        private int num4;
        private int num5;
        private int num6;
        private int bonusBall;

        private int winningLottoId = 0;
        private int money = 0;

        public Builder(int num1, int num2, int num3, int num4, int num5, int num6, int bonusBall) {
            this.num1 = num1;
            this.num2 = num2;
            this.num3 = num3;
            this.num4 = num4;
            this.num5 = num5;
            this.num6 = num6;
            this.bonusBall = bonusBall;
        }

        public Builder winningLottoId(int val) {
            this.winningLottoId = val;
            return this;
        }

        public Builder money(int val) {
            this.money = val;
            return this;
        }
    }

    public LottoGameResultDTO(Builder builder) {
        this.winningLottoId = builder.winningLottoId;
        this.num1 = builder.num1;
        this.num2 = builder.num2;
        this.num3 = builder.num3;
        this.num4 = builder.num4;
        this.num5 = builder.num5;
        this.num6 = builder.num6;
        this.bonusBall = builder.bonusBall;
        this.money = builder.money;
    }

    public LottoGameResultDTO(int bonusBall, int num1, int num2, int num3, int num4, int num5, int num6) {
        this.bonusBall = bonusBall;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
    }

    public int getWinningLottoId() {
        return winningLottoId;
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getNum3() {
        return num3;
    }

    public int getNum4() {
        return num4;
    }

    public int getNum5() {
        return num5;
    }

    public int getNum6() {
        return num6;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(Arrays.asList(num1, num2, num3, num4, num5, num6));
    }

    public int getBonusBall() {
        return bonusBall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoGameResultDTO that = (LottoGameResultDTO) o;
        return winningLottoId == that.winningLottoId &&
                num1 == that.num1 &&
                num2 == that.num2 &&
                num3 == that.num3 &&
                num4 == that.num4 &&
                num5 == that.num5 &&
                num6 == that.num6 &&
                bonusBall == that.bonusBall &&
                money == that.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLottoId, num1, num2, num3, num4, num5, num6, bonusBall, money);
    }
}
