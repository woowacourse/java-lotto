package model.vo;

import constant.LottoConfig;

public class LottoNumber {
    private final int number;

    public LottoNumber(int number) {
        validateNumberRange(number);
        this.number = number;
    }

    private void validateNumberRange(int value) {
        if (value < LottoConfig.LOTTO_MIN_NUMBER || value > LottoConfig.LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException("1에서 45 사이의 정수를 입력해주세요.");
        }
    }

    public int getNumber() {
        return number;
    }
}
