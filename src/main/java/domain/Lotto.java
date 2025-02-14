package domain;

import constant.LottoConstants;
import java.util.List;
import validator.Validator;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        for (Integer number : numbers)
            Validator.checkOutOfRange(number, LottoConstants.LOTTO_NUMBER_START, LottoConstants.LOTTO_NUMBER_END,
                    "로또의 숫자가 1~45의 유효 범위를 벗어납니다.");

        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
