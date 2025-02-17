package domain;

import dto.LottoMatchResult;
import java.util.HashSet;
import java.util.List;
import util.NumberPicker;

public class Lotto {

    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 45;
    private static final int NUMBER_COUNT = 6;
    private static final int PRICE = 1000;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateNumberNotDuplicated(numbers);
        this.numbers = numbers;
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개가 되어야 합니다.");
        }
    }

    private void validateNumberNotDuplicated(List<Integer> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException("로또 번호는 중복되면 안됩니다.");
        }
    }

    public static int getPurchaseableCount(int money) {
        return money / PRICE;
    }

    public static Lotto purchase(NumberPicker numberPicker) {
        List<Integer> numbers = numberPicker.pickUnique(START_NUMBER, END_NUMBER, NUMBER_COUNT);
        return new Lotto(numbers);
    }

    public LottoMatchResult getMatchResult(List<Integer> matchNumbers, int bonusNumber) {
        validateMatchNumbersNotDuplicated(matchNumbers);
        validateMatchNumbersSize(matchNumbers);
        validateBonusNumberNotDuplicated(matchNumbers, bonusNumber);
        int matchCount = 0;

        for (Integer matchNumber : matchNumbers) {
            if (numbers.contains(matchNumber)) {
                matchCount++;
            }
        }

        return new LottoMatchResult(matchCount, numbers.contains(bonusNumber));
    }

    private void validateMatchNumbersNotDuplicated(List<Integer> matchNumbers) {
        if (matchNumbers.size() != new HashSet<>(matchNumbers).size()) {
            throw new IllegalArgumentException("로또 당첨 번호는 중복되면 안됩니다.");
        }
    }

    private void validateMatchNumbersSize(List<Integer> matchNumbers) {
        if (matchNumbers.size() != NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 당첨 번호는 6개여야 합니다.");
        }
    }

    private void validateBonusNumberNotDuplicated(List<Integer> matchNumbers, int bonusNumber) {
        if (matchNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 중복되면 안됩니다.");
        }
    }

    private List<Integer> getSorted() {
        return numbers.stream()
                .sorted()
                .toList();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return getSorted().toString();
    }
}
