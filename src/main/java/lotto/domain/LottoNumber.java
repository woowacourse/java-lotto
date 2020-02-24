package lotto.domain;

import java.util.*;

/**
 * LottoNumber 클래스
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/21
 */
public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MINIMUM_LOTTO_NUMBER = 1;
    public static final int MAXIMUM_LOTTO_NUMBER = 45;

    private static final List<LottoNumber> CACHE;

    private final int lottoNumber;

    static {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = MINIMUM_LOTTO_NUMBER; i <= MAXIMUM_LOTTO_NUMBER; i++) {
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
            .findAny()
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
