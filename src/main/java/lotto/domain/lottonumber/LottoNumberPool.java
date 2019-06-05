package lotto.domain.lottonumber;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class LottoNumberPool {
    private static Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();

    static {
        IntStream.rangeClosed(LottoNumber.FIRST_NUMBER, LottoNumber.LAST_NUMBER)
                .forEach(number -> lottoNumbers.put(number, LottoNumber.of(number)));
    }

    public static LottoNumber valueOf(int number) {
        return lottoNumbers.get(number);
    }
}
