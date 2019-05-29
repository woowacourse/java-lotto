package lotto.model.creator;

import lotto.model.object.BonusBall;
import lotto.model.object.LottoNumber;

public class BonusBallCreator {
        private LottoNumber number;

        public static BonusBall create(int number) {
                return new BonusBall(LottoNumberCreator.create(number));
        }
}
