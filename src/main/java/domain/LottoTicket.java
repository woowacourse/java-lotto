package domain;

import java.util.List;
import view.InputErrorMessage;


public class LottoTicket {
    public static int LOTTO_SIZE = 6;
    public static int LOTTO_PRICE = 1000;
    public static int LOTTO_MIN_NUMBER = 1;
    public static int LOTTO_MAX_NUMBER = 45;

    private final List<Integer> numbers;

    private LottoTicket(List<Integer> numbers) {
        validateLottoSize(numbers);
        numbers.forEach(this::validateLottoNumberRange);
        validateDuplicateNumber(numbers);
        this.numbers = numbers;
    }

    public static LottoTicket from(List<Integer> numbers) {
        return new LottoTicket(numbers);
    }


    private void validateLottoSize(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new IllegalArgumentException(InputErrorMessage.REQUIRED_SIX_NUMBERS.getMessage());
        }
    }

    private void validateLottoNumberRange(Integer number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(InputErrorMessage.NOT_FITTED_RANGE.getMessage());
        }
    }

    private void validateDuplicateNumber(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != LOTTO_SIZE) {
            throw new IllegalArgumentException(InputErrorMessage.DUPLICATED.getMessage());
        }
    }

    public int countMatchedNumbers(List<Integer> winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean hasBonusNumber(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public int getSize() {
        return numbers.size();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
