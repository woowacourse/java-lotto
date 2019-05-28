package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class LottoNumber {
        private final int number;
        private static final Map<Integer, LottoNumber> MAPPING_LOTTO_NUMBER = new HashMap<Integer, LottoNumber>() {
                {
                        for (int number = 1; number <= 45; number++) {
                                put(number, new LottoNumber(number));
                        }
                }
        };

        private LottoNumber(int number) {
                this.number = number;
        }

        public static LottoNumber create(int number) {
                checkValidLottoNumber(number);
                return MAPPING_LOTTO_NUMBER.get(number);
        }

        private static void checkValidLottoNumber(int number) {
                if (MAPPING_LOTTO_NUMBER.get(number) == null) {
                        throw new InvalidLottoNumberException("로또 숫자 범위를 벗어났습니다.");
                }
        }
}
