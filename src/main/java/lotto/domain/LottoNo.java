package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNo implements Comparable<LottoNo> {
    public static final int MIN_NO = 1;
    public static final int MAX_NO = 45;
    private static final Map<Integer, LottoNo> lottoNumbers = new HashMap<>();
    private int no;

    private LottoNo(int no) {
        if (no < MIN_NO || no > MAX_NO) {
            throw new IllegalArgumentException(String.format("로또 번호 범위는 %d ~ %d 입니다.", MIN_NO, MAX_NO));
        }
        this.no = no;
    }

    public static LottoNo of(int no) {
        if (!lottoNumbers.containsKey(no)) {
            lottoNumbers.put(no, new LottoNo(no));
        }
        return lottoNumbers.get(no);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNo)) return false;
        LottoNo that = (LottoNo) o;
        return no == that.no;
    }

    @Override
    public int hashCode() {
        return Objects.hash(no);
    }

    @Override
    public String toString() {
        return Integer.toString(no);
    }

    @Override
    public int compareTo(LottoNo target) {
        return no - target.no;
    }
}
