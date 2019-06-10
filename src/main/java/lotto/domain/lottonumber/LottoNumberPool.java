package lotto.domain.lottonumber;

import java.util.HashMap;
import java.util.Map;

public class LottoNumberPool {
    private static Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();

    private LottoNumberPool() {
    }

    public static LottoNumber valueOf(int number) {
        if (lottoNumbers.containsKey(number)) {
            return lottoNumbers.get(number);
        }

        LottoNumber lottoNumber = LottoNumber.of(number);
        lottoNumbers.put(number, lottoNumber);
        return lottoNumber;
    }
}
