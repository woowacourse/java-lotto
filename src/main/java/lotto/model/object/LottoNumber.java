package lotto.model.object;

import lotto.model.exception.InvalidLottoNumberException;

import java.util.HashMap;
import java.util.Map;

public class LottoNumber implements Comparable<LottoNumber> {
        public static final int MAX_LOTTO_NUMBER = 45;

        private final int number;

        private static final Map<Integer, LottoNumber> MAPPING_LOTTO_NUMBER = new HashMap<Integer, LottoNumber>() {
                {
                        for (int number = 1; number <= MAX_LOTTO_NUMBER; number++) {
                                put(number, new LottoNumber(number));
                        }
                }
        };

        private LottoNumber(final int number) {
                this.number = number;
        }

        public static LottoNumber getInstance(final int number){
                checkValidLottoNumber(number);
                return MAPPING_LOTTO_NUMBER.get(number);
        }

        private static void checkValidLottoNumber(final int number) {
                if (LottoNumber.MAPPING_LOTTO_NUMBER.get(number) == null) {
                        throw new InvalidLottoNumberException("로또 숫자 범위를 벗어났습니다.");
                }
        }

        public int getNumber() {
                return number;
        }

        @Override
        public int compareTo(LottoNumber o) {
                return this.number - o.number;
        }
}
