package lotto.model.creator;

import lotto.model.exception.InvalidLottoNumberException;
import lotto.model.object.LottoNumber;

public class LottoNumberCreator {
        public static LottoNumber create(int number) {
                checkValidLottoNumber(number);
                return LottoNumber.MAPPING_LOTTO_NUMBER.get(number);
        }

        private static void checkValidLottoNumber(int number) {
                if (LottoNumber.MAPPING_LOTTO_NUMBER.get(number) == null) {
                        throw new InvalidLottoNumberException("로또 숫자 범위를 벗어났습니다.");
                }
        }
}

