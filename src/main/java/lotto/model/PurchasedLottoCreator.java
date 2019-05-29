package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PurchasedLottoCreator {
        private static final int NUMBER_OF_LOTTO_NUMBERS = 6;

        public static Lotto create() {
                List<LottoNumber> lottoNumbers = new ArrayList<>();
                List<Integer> numbers = new ArrayList<>(LottoNumber.MAPPING_LOTTO_NUMBER.keySet());
                Collections.shuffle(numbers);
                numbers = numbers.subList(0,6);
                Collections.sort(numbers);
                for (int index = 0; index < NUMBER_OF_LOTTO_NUMBERS; index++) {
                        lottoNumbers.add(LottoNumberCreator.create(numbers.get(index)));
                }
                return new Lotto(lottoNumbers);
        }
}
