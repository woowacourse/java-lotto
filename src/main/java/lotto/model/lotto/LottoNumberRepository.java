package lotto.model.lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static lotto.model.lotto.LottoNumber.MIN_LOTTO_NUMBER;
import static lotto.model.lotto.LottoNumber.MAX_LOTTO_NUMBER;

public class LottoNumberRepository {
    private static Map<Integer, LottoNumber> lottoNumberRepository = new HashMap<>();

    static {
        IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .forEach(number -> lottoNumberRepository.put(number, LottoNumber.from(number)));
    }

    public static LottoNumber fromNumber(int number) {
        return lottoNumberRepository.get(number);
    }
}
