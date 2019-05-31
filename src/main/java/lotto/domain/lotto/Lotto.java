package lotto.domain.lotto;

import java.util.List;

public class Lotto {
    public static final int NUMBER_LENGTH = 6;
    public static final int PRICE = 1000;
    List<LottoNumber> numbers;


    public Lotto(List<Integer> numbers) {
        checkDuplication(numbers);
        checkLottoLength(numbers);
        this.numbers = LottoNumber.convertNumbersToLottoNumbers(numbers);
    }

    private void checkDuplication(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalNumberCombinationException();
        }
    }

    private void checkLottoLength(List<Integer> numbers) {
        if (numbers.size() != NUMBER_LENGTH) {
            throw new IllegalNumberCombinationException();
        }
    }


    public int countMatchLottoNumber(Lotto lotto) {
        return (int) numbers.stream()
                .filter(number -> lotto.containsNumber(number))
                .count();
    }

    public boolean containsNumber(LottoNumber number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
