package domain;

import constant.LottoConstants;
import java.util.Collections;
import java.util.List;
import validator.Validator;

public class Lotto {

    private static final String LOTTO_OUT_OF_RANGE = String.format(
            "로또의 숫자가 %d~%d의 유효 범위를 벗어납니다.",
            LottoConstants.LOTTO_NUMBER_START,
            LottoConstants.LOTTO_NUMBER_END
    );

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        numbers.forEach(number -> Validator.checkOutOfRange(number, LottoConstants.LOTTO_NUMBER_START, LottoConstants.LOTTO_NUMBER_END,
                LOTTO_OUT_OF_RANGE));
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
