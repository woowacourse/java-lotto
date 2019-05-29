package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class LottoNumber {
        private final int number;

        public static final Map<Integer, LottoNumber> MAPPING_LOTTO_NUMBER = new HashMap<Integer, LottoNumber>() {
                {
                        for (int number = 1; number <= 45; number++) {
                                put(number, new LottoNumber(number));
                        }
                }
        };

        private LottoNumber(int number) {
                this.number = number;
        }
}
