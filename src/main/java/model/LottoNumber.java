package model;

import static constant.LottoConstant.MAX_LOTTO_NUMBER;
import static constant.LottoConstant.MIN_LOTTO_NUMBER;

public record LottoNumber(int value) implements Comparable<LottoNumber> {

    public LottoNumber {
        if (value < MIN_LOTTO_NUMBER || value > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(String.format("로또 번호는 %d 이상 %d 이하여야 합니다.",
                    MIN_LOTTO_NUMBER,
                    MAX_LOTTO_NUMBER
            ));
        }
    }

    @Override
    public int compareTo(LottoNumber o) {
        return this.value - o.value;
    }
}
