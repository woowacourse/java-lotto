package lotto.dto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LottoDTO {
    private int lottoId;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;

    public LottoDTO(int lottoId, int num1, int num2, int num3, int num4, int num5, int num6) {
        this.lottoId = lottoId;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
    }

    public LottoDTO(int num1, int num2, int num3, int num4, int num5, int num6) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
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
}
