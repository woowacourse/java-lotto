package lotto.domain.lotto;

import lotto.domain.InvalidRangeException;

import java.util.Objects;

public class LottoNo {
    public static final int MAX_NO = 45;
    public static final int MIN_NO = 1;

    private final int no;

    public LottoNo(int no) {
        validateRange(no);
        this.no = no;
    }

    private void validateRange(int no) {
        if (no < MIN_NO || no > MAX_NO) {
            throw new InvalidRangeException("잘못된 범위의 번호입니다.");
        }
    }

    public int compareTo(LottoNo lottoBall) {
        return Integer.compare(no, lottoBall.no);
    }

    int getNo() {
        return no;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNo lottoBall = (LottoNo) o;
        return no == lottoBall.no;
    }

    @Override
    public int hashCode() {
        return Objects.hash(no);
    }
}