package lotto.db.dto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoDTO {
    private int lottoId;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoDTO lottoDTO = (LottoDTO) o;
        return lottoId == lottoDTO.lottoId &&
                num1 == lottoDTO.num1 &&
                num2 == lottoDTO.num2 &&
                num3 == lottoDTO.num3 &&
                num4 == lottoDTO.num4 &&
                num5 == lottoDTO.num5 &&
                num6 == lottoDTO.num6;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoId, num1, num2, num3, num4, num5, num6);
    }
}
