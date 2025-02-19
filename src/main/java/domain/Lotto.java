package domain;

import static error.ErrorMessage.DUPLICATE_NUMBERS_FOUND;
import static error.ErrorMessage.INVALID_LOTTO_COUNT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private final List<LottoNumber> numbers;
    private PrizeTier prizeTier = null;

    public Lotto(List<LottoNumber> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    public int countMatches(List<LottoNumber> winningNumbers) {
        Set<LottoNumber> lottoNumbers = new HashSet<>(this.numbers);
        Set<LottoNumber> winningLottoNumbers = new HashSet<>(winningNumbers);
        lottoNumbers.retainAll(winningLottoNumbers);
        return lottoNumbers.size();
    }

    public void rankTier(WinningLotto winningLotto) {
        int count = winningLotto.getMatchCount(numbers);
        boolean isBonusMatched = winningLotto.isBonusMatched(numbers);
        this.prizeTier = PrizeTier.getTier(count, isBonusMatched);
    }

    public PrizeTier getPrizeTier() {
        return prizeTier;
    }

    public boolean isTierMatched(PrizeTier tier) {
        return this.prizeTier == tier;
    }

    public boolean isBonusMatched(LottoNumber bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }

    private void validateNumbers(List<LottoNumber> numbers) {
        validateDuplicateNumbers(numbers);
        validateNumbersCount(numbers);
    }

    private void validateNumbersCount(List<LottoNumber> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(INVALID_LOTTO_COUNT.getMessage());
        }
    }

    private void validateDuplicateNumbers(List<LottoNumber> numbers) {
        Set<LottoNumber> numSet = new HashSet<>(numbers);
        if (numSet.size() != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_NUMBERS_FOUND.getMessage());
        }
    }
}
