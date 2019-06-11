package lotto.domain.creator;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

import java.util.List;

public class AutoLottoCreator implements LottoCreator {
    public AutoLottoCreator() {}

    @Override
    public Lotto createLotto() {
        List<LottoNumber> numberList = LottoNumber.getRandomSixNumbers();

        return new Lotto(numberList);
    }
}
