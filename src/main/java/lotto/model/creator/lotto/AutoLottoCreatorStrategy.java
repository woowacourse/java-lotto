package lotto.model.creator.lotto;

import lotto.model.Lotto;
import lotto.model.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoLottoCreatorStrategy implements LottoCreatorStrategy {
        private static final AutoLottoCreatorStrategy INSTANCE = new AutoLottoCreatorStrategy();

        private AutoLottoCreatorStrategy() {
        }

        public static AutoLottoCreatorStrategy getInstance() {
                return INSTANCE;
        }

        @Override
        public Lotto create() {
                List<LottoNumber> autoCreatedNumbers = new ArrayList<>();
                for (int i = 1; i < LottoNumber.MAX_LOTTO_NUMBER; i++) {
                        autoCreatedNumbers.add(LottoNumber.getInstance(i));
                }
                Collections.shuffle(autoCreatedNumbers);
                autoCreatedNumbers = autoCreatedNumbers.subList(0, Lotto.NUMBER_OF_LOTTO_NUMBERS);
                Collections.sort(autoCreatedNumbers);
                return new Lotto(autoCreatedNumbers);
        }
}
