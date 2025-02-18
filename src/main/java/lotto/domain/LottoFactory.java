package lotto.domain;

import static lotto.util.Constant.LOTTO_NUMBER_DELIMITER;

import java.util.List;
import lotto.util.StringConverter;

public class LottoFactory {

    public Lotto createLotto(String input) {
        List<Integer> numbers = StringConverter.parseToIntList(input, LOTTO_NUMBER_DELIMITER);
        return new Lotto(numbers);
    }
}
