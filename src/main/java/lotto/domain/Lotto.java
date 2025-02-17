package lotto.domain;

import static lotto.exception.ExceptionMessage.INVALID_LOTTO_SIZE;
import static lotto.exception.ExceptionMessage.MUST_NOT_BE_DUPLICATED_LOTTO_NUMBER;
import static lotto.exception.ExceptionMessage.OUT_OF_RANGE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.LottoException;

public class Lotto {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;
    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
        validateNumberRange();
        validateLottoSize();
        validateDuplicateNumber();
    }

    public int checkMatchCount(List<Integer> winningNumbers) {
        int count = 0;
        for (int lottoNumber : lottoNumbers) {
            if (winningNumbers.contains(lottoNumber)) {
                count += 1;
            }
        }
        return count;
    }

    public boolean checkBonus(int bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    private void validateNumberRange() {
        for (Integer number : lottoNumbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new LottoException(OUT_OF_RANGE);
            }
        }
    }

    private void validateDuplicateNumber() {
        Set<Integer> set = new HashSet<>(lottoNumbers);
        if (set.size() != LOTTO_SIZE) {
            throw new LottoException(MUST_NOT_BE_DUPLICATED_LOTTO_NUMBER);
        }
    }

    private void validateLottoSize() {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new LottoException(INVALID_LOTTO_SIZE);
        }
    }

    public int getSize() {
        return lottoNumbers.size();
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
