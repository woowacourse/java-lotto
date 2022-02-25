package lotto.model;

import java.util.List;

public class Lotto {

    private static final String LOTTO_ERROR_MESSAGE = "[ERROR] 잘못된 로또 번호입니다.";

    private final List<Integer> numbers;
    private Rank rank;

    public Lotto(List<Integer> numbers) {
        validateLottoNumbers(numbers);
        this.numbers = numbers;
        this.rank = Rank.LOSER;
    }

    private void validateLottoNumbers(List<Integer> numbers) {
        if (!isValidLength(numbers) || !isValidRange(numbers) || isDuplicate(numbers)) {
            throw new RuntimeException(LOTTO_ERROR_MESSAGE);
        }
    }

    private boolean isValidLength(List<Integer> numbers) {
        return numbers.size() == 6;
    }

    private boolean isValidRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                return false;
            }
        }
        return true;
    }

    private boolean isDuplicate(List<Integer> numbers) {
        return numbers.size() != numbers.stream().distinct().count();
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
