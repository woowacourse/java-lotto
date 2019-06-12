package lotto.dto;

import java.util.Objects;

public class WinningLottoDTO {
    private int winningLottoId;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;
    private int bonusBall;

    public WinningLottoDTO(int winningLottoId, int num1, int num2, int num3, int num4, int num5, int num6, int bonusBall) {
        this.winningLottoId = winningLottoId;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
        this.bonusBall = bonusBall;
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

    public int getBonusBall() {
        return bonusBall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLottoDTO that = (WinningLottoDTO) o;
        return winningLottoId == that.winningLottoId &&
                num1 == that.num1 &&
                num2 == that.num2 &&
                num3 == that.num3 &&
                num4 == that.num4 &&
                num5 == that.num5 &&
                num6 == that.num6 &&
                bonusBall == that.bonusBall;
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLottoId, num1, num2, num3, num4, num5, num6, bonusBall);
    }
}
