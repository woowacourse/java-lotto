package lotto.domain.creator;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

import java.util.Collections;
import java.util.List;

import static lotto.domain.lotto.Lotto.NUMBER_OF_NUMBERS_IN_LOTTO;

public class AutoLottoCreator implements LottoCreator {
    public AutoLottoCreator() {}

    @Override
    public Lotto createLotto() {
        List<LottoNumber> allNumbers = LottoNumber.getAllLottoNumbers();

        Collections.shuffle(allNumbers);

        return new Lotto(allNumbers.subList(0, NUMBER_OF_NUMBERS_IN_LOTTO));
    }
}
