package domain;

import java.util.HashMap;
import java.util.Map;

public interface LottoGenerator {
    int LOTTO_SIZE = 6;
    int MAX_LOTTO_NUMBER = 45;
    int MIN_LOTTO_NUMBER = 1;
    Map<Integer, LottoNumber> allLottoNumbers = new HashMap<Integer, LottoNumber>(){{
        for (int number = MIN_LOTTO_NUMBER; number <= MAX_LOTTO_NUMBER; number++) {
            allLottoNumbers.put(number, new LottoNumber(number));
        }
    }};

    Lotto generateLotto();
}
