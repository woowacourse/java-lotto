package lotto.domain.generator;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static lotto.domain.LottoNumber.*;

public class LottoGenerator {
    public static Lotto generateLotto() {
        List<LottoNumber> allLottoNumbers = getAllLottoNumbers();
        Collections.shuffle(allLottoNumbers);
        List<LottoNumber> numbers = allLottoNumbers.subList(0,6);
        numbers.sort(Comparator.comparingInt(LottoNumber::getNumber));
        return new Lotto(numbers);
    }
}
