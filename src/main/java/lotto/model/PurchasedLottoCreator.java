package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PurchasedLottoCreator {
        private static final int NUMBER_OF_LOTTO_NUMBERS = 6;
        private static final int MAX_LOTTO_NUMBER = 45;

        public static Lotto create() {
                List<Integer>numbers = new ArrayList<>(LottoNumber.MAPPING_LOTTO_NUMBER.keySet());
                Collections.shuffle(numbers);
                List<LottoNumber> lottoNumbers = new ArrayList<>();
                for(int index=0;i<NUMBER_OF_LOTTO_NUMBERS;i++){
                        lottoNumbers.add(LottoNumberCreator.create(numbers.get(index)));
                }
                return new Lotto(lottoNumbers);
        }
}
