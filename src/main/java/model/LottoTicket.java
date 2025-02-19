package model;

import static common.LottoTicketConstant.LOTTO_MAX_NUMBER;
import static common.LottoTicketConstant.LOTTO_MIN_NUMBER;
import static common.LottoTicketConstant.LOTTO_NUMBER_SIZE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LottoTicket {

    private final List<Integer> numbers;

    public LottoTicket(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }

    private void validateNumbers(List<Integer> numbers) {
        numbers.forEach(this::validateRange);
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private void validateRange(int number) {
        if (number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 번호는 1부터 45 사이여야 합니다.");
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복되어서는 안됩니다.");
        }
    }
}
