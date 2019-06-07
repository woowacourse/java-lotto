package lotto.domain.TicketModel;

import lotto.domain.Exceptions.LottoNumberException;

import java.util.List;

class LottoNumbers {
    private List<Integer> numbers;

    LottoNumbers() {
        this.numbers = LottoNumberManager.autoNumber();
    }

    LottoNumbers(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (!LottoNumberManager.check(numbers)) {
            throw new LottoNumberException();
        }
    }

    List<Integer> numbers() {
        return numbers;
    }
}