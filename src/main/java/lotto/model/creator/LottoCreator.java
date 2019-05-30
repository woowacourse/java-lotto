package lotto.model.creator;

import lotto.model.exception.LottoNumberDuplicationException;
import lotto.model.object.Lotto;
import lotto.model.object.LottoNumber;

import java.util.ArrayList;
import java.util.List;

public class LottoCreator {
        private static final int NUMBER_OF_LOTTO_NUMBERS = 6;

        public static Lotto create(String[] inputs) {
                List<LottoNumber> lottoNumbers = new ArrayList<>();
                for (String input : inputs) {
                        int number = Integer.parseInt(input);
                        lottoNumbers.add(LottoNumberCreator.create(number));
                }
                checkLottoNumbersDuplication(lottoNumbers);
                return new Lotto(lottoNumbers);
        }

        private static void checkLottoNumbersDuplication(List<LottoNumber> lottoNumbers) {
                for (int index = 0; index < NUMBER_OF_LOTTO_NUMBERS - 1; index++) {
                        checkEachIndexLottoNumberDuplication(lottoNumbers, index);
                }
        }

        private static void checkEachIndexLottoNumberDuplication(List<LottoNumber> lottoNumbers, int index) {
                List<LottoNumber> remainder = lottoNumbers.subList(index + 1, NUMBER_OF_LOTTO_NUMBERS);
                if (remainder.contains(lottoNumbers.get(index))) {
                        throw new LottoNumberDuplicationException("로또 숫자가 중복되었습니다.");
                }
        }
}
