package lotto.model.object;

import java.util.HashMap;
import java.util.Map;

public class LottoNumber {
        private static final int MAX_LOTTO_NUMBER = 45;
        private final int number;

        private LottoNumber(int number) {
                this.number = number;
        }

        private static final Map<Integer, LottoNumber> MAPPING_LOTTO_NUMBER = new HashMap<Integer, LottoNumber>() {
                {
                        for (int number = 1; number <= MAX_LOTTO_NUMBER; number++) {
                                put(number, new LottoNumber(number));
                        }
                }
        };

        public LottoNumber getInstance(int number){
                return MAPPING_LOTTO_NUMBER.get(number);
        }

        public int getNumber() {
                return number;
        }
}
