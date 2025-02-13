package domain;

import java.util.List;


public class LottoTicket {
    public static int LOTTO_SIZE = 6;
    public static int LOTTO_PRICE = 1000;
    public static int LOTTO_MIN_NUMBER = 1;
    public static int LOTTO_MAX_NUMBER = 45;

    private final List<Integer> numbers;

    public LottoTicket(List<Integer> numbers) {
        validateLottoSize(numbers);
        numbers.forEach(this::validateLottoNumberRange);
        validateDuplicateNumber(numbers);
        this.numbers = numbers;
    }

    private void validateLottoSize(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateLottoNumberRange(Integer number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("로또 번호는 1 이상 45 이하이다.");
        }
    }
    
    private void validateDuplicateNumber(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != LOTTO_SIZE) {
            throw new IllegalArgumentException("중봉된 번호가 존재합니다.");
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
