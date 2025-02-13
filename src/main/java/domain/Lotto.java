package domain;

import constant.LottoConstants;
import java.util.List;
import validator.ErrorMessages;
import validator.Validator;

public class Lotto {

    private List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        for (int idx = 0; idx < numbers.size(); idx++)
            Validator.checkOutOfRange(numbers.get(idx), LottoConstants.LOTTO_NUMBER_START, LottoConstants.LOTTO_NUMBER_END, ErrorMessages.LOTTO_NUMBER_OUT_OF_RANGE.getMessage());

        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}