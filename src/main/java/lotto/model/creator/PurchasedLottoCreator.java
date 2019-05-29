package lotto.model.creator;

import lotto.model.object.Lotto;
import lotto.model.object.LottoNumber;

import java.util.ArrayList;
import java.util.List;

public class PurchasedLottoCreator {
        private static final int NUMBER_OF_LOTTO_NUMBERS = 6;

        public static Lotto create(List<Integer> number) {
                List<LottoNumber> lottoNumbers = new ArrayList<>();
                List<Integer> autoCreatedNumbers = number;
                for (int index = 0; index < NUMBER_OF_LOTTO_NUMBERS; index++) {
                        lottoNumbers.add(LottoNumberCreator.create(autoCreatedNumbers.get(index)));
                }
                return new Lotto(lottoNumbers);
        }
}
