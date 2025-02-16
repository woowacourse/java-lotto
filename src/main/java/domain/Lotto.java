package domain;

import constant.LottoConstants;
import java.util.List;
import validator.Validator;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        numbers.forEach(number -> Validator.checkOutOfRange(number, LottoConstants.LOTTO_NUMBER_START, LottoConstants.LOTTO_NUMBER_END,
                String.format("로또의 숫자가 %d~%d의 유효 범위를 벗어납니다.", LottoConstants.LOTTO_NUMBER_START, LottoConstants.LOTTO_NUMBER_END)));
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
