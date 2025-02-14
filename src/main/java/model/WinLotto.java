package model;

import constant.ErrorMessage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinLotto {
    private final LottoNumbers lottoNumbers;
    private final Integer bonusNumber;

    public WinLotto(List<Integer> winNumbers, Integer bonusNumber) {
        validateWinNumbers(winNumbers);
        validateBound(bonusNumber);
        validateDuplicate(winNumbers, bonusNumber);
        this.lottoNumbers = new LottoNumbers(winNumbers);
        this.bonusNumber = bonusNumber;
    }

    public Integer countMatchNumber(LottoNumbers lottoNumbers) {
        return this.lottoNumbers.countMatchNumber(lottoNumbers);
    }

    public Boolean bonusMatch(LottoNumbers lottoNumbers) {
        return lottoNumbers.bonusMatch(this.bonusNumber);
    }

    private void validateWinNumbers(List<Integer> winNumbers) {
        validateNumberCount(winNumbers);
        winNumbers.forEach(this::validateBound);
        validateDuplicate(winNumbers);
    }

    private void validateNumberCount(List<Integer> winNumbers) {
        if (winNumbers.size() != LottoNumbers.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_COUNT_EXCEPTION);
        }
    }

    private void validateDuplicate(List<Integer> winNumbers, Integer bonusNumber) {
        if (winNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_DUPLICATE_EXCEPTION);
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numbers.size() != numberSet.size()) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_DUPLICATE_EXCEPTION);
        }
    }

    private void validateBound(Integer input) {
        if (input < LottoNumbers.MINIMUM_LOTTO_NUMBER || input > LottoNumbers.MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_BOUND_EXCEPTION);
        }
    }

}
