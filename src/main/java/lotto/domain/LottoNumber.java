package lotto.domain;

import java.util.Objects;

public class LottoNumber {

    private static final int MIN_LOTTO_NUM = 1;
    private static final int MAX_LOTTO_NUM = 45;

    private final int lottoNum;

    public static LottoNumber fromStringLottoNumber(String lottoNum) {
        lottoNum = lottoNum.trim();

        if (!isInteger(lottoNum)) {
            throw new IllegalArgumentException("1~45의 숫자만 입력해야 합니다.");
        }

        return from(Integer.parseInt(lottoNum));
    }

    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static LottoNumber from(int lottoNum) {
        if (!isInRange(lottoNum)) {
            throw new IllegalArgumentException("1~45의 숫자만 입력해야 합니다.");
        }

        return new LottoNumber(lottoNum);
    }

    public static boolean isInRange(int lottoNum) {
        return lottoNum >= MIN_LOTTO_NUM && lottoNum <= MAX_LOTTO_NUM;
    }

    private LottoNumber(int lottoNum) {
        this.lottoNum = lottoNum;
    }

    public int getLottoNum() {
        return lottoNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return lottoNum == that.lottoNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNum);
    }
}
