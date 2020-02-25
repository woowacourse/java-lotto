package lotto.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    private static final Map<Integer, LottoNumber> LOTTO_NUMBERS;

    private final int lottoNumber;

    static {
        Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();
        for (int i = MINIMUM_LOTTO_NUMBER; i <= MAXIMUM_LOTTO_NUMBER; i++) {
            lottoNumbers.put(i, new LottoNumber(i));
        }
        LOTTO_NUMBERS = Collections.unmodifiableMap(lottoNumbers);
    }

    private LottoNumber(int inputLottoNumber) {
        this.lottoNumber = inputLottoNumber;
    }

    public static LottoNumber of(final int lottoNumber) {
        if (!LOTTO_NUMBERS.containsKey(lottoNumber)) {
            throw new WrongLottoNumberException("유효한 로또 번호가 아닙니다.");
        }
        return LOTTO_NUMBERS.get(lottoNumber);
    }

    public int getLottoNumber() {
        return this.lottoNumber;
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return this.lottoNumber - lottoNumber.lottoNumber;
    }
}
