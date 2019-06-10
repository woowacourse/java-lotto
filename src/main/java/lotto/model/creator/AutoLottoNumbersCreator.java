package lotto.model.creator;

import lotto.model.object.Lotto;
import lotto.model.object.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoLottoNumbersCreator {
        public static List<LottoNumber> create() {
                List<LottoNumber> autoCreatedNumbers = new ArrayList<>();
                for (int i = 1; i < LottoNumber.MAX_LOTTO_NUMBER; i++) {
                        autoCreatedNumbers.add(LottoNumber.getInstance(i));
                }
                Collections.shuffle(autoCreatedNumbers);
                autoCreatedNumbers = autoCreatedNumbers.subList(0, Lotto.NUMBER_OF_LOTTO_NUMBERS);
                Collections.sort(autoCreatedNumbers);
                return autoCreatedNumbers;
        }
}
