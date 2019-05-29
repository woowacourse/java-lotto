package lotto.model.object;

import java.util.HashMap;
import java.util.Map;

                public class LottoNumber {
                        public static final int MAX_LOTTO_NUMBER = 45;
                        private final int number;

                        public static final Map<Integer, LottoNumber> MAPPING_LOTTO_NUMBER = new HashMap<Integer, LottoNumber>() {
                                {
                                        for (int number = 1; number <= MAX_LOTTO_NUMBER; number++) {
                                                put(number, new LottoNumber(number));
                                        }
                                }
                        };

                        private LottoNumber(int number) {
                                this.number = number;
                        }

                        public int getNumber() {
                return number;
        }
}
