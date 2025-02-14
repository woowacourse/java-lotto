package model;

import dto.LottoDto;
import error.ErrorMessage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private List<Integer> numbers;
    private PrizeTier prizeTier = null;

    public Lotto(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    public int countMatches(List<Integer> winningNumbers) {
        Set<Integer> numberSet = new HashSet<>(this.numbers);
        Set<Integer> winningNumberSet = new HashSet<>(winningNumbers);
        numberSet.retainAll(winningNumberSet);
        return numberSet.size();
    }

    public void rankTier() {
        WinningLotto winningLotto = WinningLotto.getInstance();
        int count = winningLotto.getMatchCount(numbers);
        boolean isBonusMatched = winningLotto.isBonusMatched(numbers);
        this.prizeTier = PrizeTier.getTier(count, isBonusMatched);
    }

    public int extractPrize() {
        return prizeTier.getPrize();
    }

    public boolean isTierMatched(PrizeTier tier) {
        return this.prizeTier == tier;
    }

    public boolean isBonusMatched(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public LottoDto toDto() {
        return new LottoDto(new ArrayList<>(numbers));
    }

    private void validateNumbers(List<Integer> numbers) {
        validateDuplicateNumbers(numbers);
        validateNumbersRange(numbers);
        validateNumbersCount(numbers);
    }

    private void validateNumbersCount(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_COUNT.getMessage());
        }
    }

    private void validateDuplicateNumbers(List<Integer> numbers) {
        Set<Integer> numSet = new HashSet<>(numbers);
        if (numSet.size() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_NUMBERS_FOUND.getMessage());
        }
    }

    private void validateNumbersRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException(ErrorMessage.NUMBER_OUT_OF_RANGE.getMessage());
            }
        }
    }
}
