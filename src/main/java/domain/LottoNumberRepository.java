package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoNumberRepository {
    private static final Map<Integer, LottoNumber> LOTTO_NUMBERS_CACHE = new HashMap<>();

    private LottoNumberRepository() {
    }

    public static LottoNumber getLottoNumberByInt(int lottoNumber) {
        if (LOTTO_NUMBERS_CACHE.containsKey(lottoNumber)) {
            return LOTTO_NUMBERS_CACHE.get(lottoNumber);
        }

        LottoNumber lottoNumberByRequest = new LottoNumber(lottoNumber);
        LOTTO_NUMBERS_CACHE.put(lottoNumber, lottoNumberByRequest);

        return lottoNumberByRequest;
    }
}
