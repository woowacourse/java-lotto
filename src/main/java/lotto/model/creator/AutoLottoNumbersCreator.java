package lotto.model.creator;

import lotto.model.object.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoLottoNumbersCreator {
        private static final int NUMBER_OF_LOTTO_NUMBERS = 6;

        public static List<Integer> create() {
                List<Integer> autoCreatedNumbers = new ArrayList<>(LottoNumber.MAPPING_LOTTO_NUMBER.keySet());
                Collections.shuffle(autoCreatedNumbers);
                autoCreatedNumbers = autoCreatedNumbers.subList(0, NUMBER_OF_LOTTO_NUMBERS);
                Collections.sort(autoCreatedNumbers);
                return autoCreatedNumbers;
        }
}
