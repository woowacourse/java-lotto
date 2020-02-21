package lotto.domain;

import java.util.*;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/21
 */
public class LottoNumber implements Comparable<LottoNumber> {
    private static final List<LottoNumber> CACHE;

    private final int lottoNumber;

    static {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        CACHE = Collections.unmodifiableList(lottoNumbers);
    }

    private LottoNumber(int inputLottoNumber) {
        this.lottoNumber = inputLottoNumber;
    }

    public static LottoNumber of(final int lottoNumber) {
        return CACHE.stream()
            .filter(value -> value.lottoNumber == lottoNumber)
            .findFirst()
            .orElseThrow(() -> new WrongLottoNumberException("유효한 로또 번호가 아닙니다."));
    }

    public static List<LottoNumber> getCache() {
        return new ArrayList<>(CACHE);
    }

    public int getLottoNumber() {
        return this.lottoNumber;
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return this.lottoNumber - lottoNumber.lottoNumber;
    }
}
