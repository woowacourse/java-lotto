package lotto.domain.lotto;

import lotto.domain.InvalidRangeException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNo {
    private static final Map<Integer, LottoNo> lottoNoPool = new HashMap<>();
    public static final int MAX_NO = 45;
    public static final int MIN_NO = 1;

    static {
        IntStream.range(MIN_NO, MAX_NO).forEach(i -> lottoNoPool.put(i, new LottoNo(i)));
    }

    private static final String INVALID_RANGE_ERROR_MSG = "잘못된 범위의 번호입니다.";

    private final int no;

    private LottoNo(int no) {
        validateRange(no);
        this.no = no;
    }

    public static LottoNo of(int no) {
        validateRange(no);
        return lottoNoPool.get(no);
    }

    private static void validateRange(int no) {
        if (no < MIN_NO || no > MAX_NO) {
            throw new InvalidRangeException(INVALID_RANGE_ERROR_MSG);
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