package lotto.domain.lotto;

import lotto.domain.lotto.exception.IllegalNumberCombinationException;

import java.util.List;

public class Lotto {
    public static final int NUMBER_LENGTH = 6;
    public static final int PRICE = 1000;
    List<LottoNumber> numbers;


    public Lotto(List<Integer> numbers) {
        checkDuplication(numbers);
        checkLottoLength(numbers);
        this.numbers = LottoNumber.convertToLottoNumbers(numbers);
    }

    private void checkDuplication(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalNumberCombinationException("중복되는 숫자가 있습니다.");
        }
    }

    private void checkLottoLength(List<Integer> numbers) {
        if (numbers.size() != NUMBER_LENGTH) {
            throw new IllegalNumberCombinationException("로또 수의 개수는 6개여야 합니다.");
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
