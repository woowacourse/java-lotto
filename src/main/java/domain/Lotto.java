package domain;

import constant.LottoConstants;
import java.util.List;
import validator.ErrorMessages;
import validator.Validator;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        for (Integer number : numbers)
            Validator.checkOutOfRange(number, LottoConstants.LOTTO_NUMBER_START, LottoConstants.LOTTO_NUMBER_END,
                    ErrorMessages.LOTTO_NUMBER_OUT_OF_RANGE.getMessage());

        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
