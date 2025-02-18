package domain;

import static constant.LottoConstants.LOTTO_RANGE_MAX;
import static constant.LottoConstants.LOTTO_RANGE_MIN;
import static constant.LottoConstants.LOTTO_SIZE;
import static exception.ErrorMessage.LOTTO_NUMBER_DUPLICATED_ERROR;
import static exception.ErrorMessage.LOTTO_RANGE_ERROR;
import static exception.ErrorMessage.LOTTO_SIZE_ERROR;

import exception.UserInputException;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLottoRange(numbers);
        validateLottoDuplicate(numbers);
        validateLottoNumberSize(numbers);
        this.numbers = numbers;
    }

    private void validateLottoRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < LOTTO_RANGE_MIN.getValue() || number > LOTTO_RANGE_MAX.getValue()) {
                throw UserInputException.from(LOTTO_RANGE_ERROR);
            }
        }
    }

    private void validateLottoDuplicate(List<Integer> numbers) {
        HashSet<Integer> set = new HashSet<>(numbers);
        if (set.size() != numbers.size()) {
            throw UserInputException.from(LOTTO_NUMBER_DUPLICATED_ERROR);
        }
    }

    private void validateLottoNumberSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE.getValue()) {
            throw UserInputException.from(LOTTO_SIZE_ERROR);
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public boolean contains(Integer number) {
        return numbers.contains(number);
    }

}
