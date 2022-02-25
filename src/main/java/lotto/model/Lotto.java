package lotto.model;

import java.util.List;
import lotto.model.validator.Validator;
import lotto.util.RandomUtil;

public class Lotto {

    private static final String LOTTO_ERROR_MESSAGE = "[ERROR] 잘못된 로또 번호입니다.";

    private final List<Integer> numbers;
    private Rank rank;

    public Lotto(RandomUtil randomNumbersGenerator) {
        List<Integer> numbers = randomNumbersGenerator.generate();
        validateLottoNumbers(numbers);
        this.numbers = numbers;
        this.rank = Rank.LOSER;
    }

    private void validateLottoNumbers(List<Integer> numbers) {
        if (!Validator.isValidLength(numbers) || !Validator.isValidRange(numbers) || Validator.isDuplicate(numbers)) {
            throw new RuntimeException(LOTTO_ERROR_MESSAGE);
        }
    }

    public Rank calculateRank(List<Integer> winningNumbers, BonusNumber bonusNumber) {
        int count = countMatchingNumber(winningNumbers);
        boolean win = false;
        if (count == Rank.SECOND.getCount()) {
            win = containNumber(bonusNumber.getBonusNumber());
        }
        return this.rank = Rank.getRank(count, win);
    }

    private int countMatchingNumber(List<Integer> winningNumbers) {
        return (int) winningNumbers.stream()
                .filter(this::containNumber)
                .count();
    }

    private boolean containNumber(int number) {
        return this.numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Rank getRank() {
        return rank;
    }
}
