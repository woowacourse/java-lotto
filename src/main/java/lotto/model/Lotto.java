package lotto.model;

import lotto.model.exceptions.IllegalNumberCombinationException;

import java.util.List;

public class Lotto {
    public static final int LOTTO_NUMBER_LENGTH = 6;

    private List<LottoNumber> numbers;


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
        if (numbers.size() != LOTTO_NUMBER_LENGTH) {
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


    public Prize getRank(Lotto winningLotto, LottoNumber bonusNumber) {
        return Prize.getPrizeRank(countMatchLottoNumber(winningLotto), containsNumber(bonusNumber));
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
