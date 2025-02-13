package lotto.domain;

import static lotto.exception.ErrorMessage.MUST_NOT_BE_DUPLICATED;
import static lotto.exception.ErrorMessage.OUT_OF_RANGE;
import static lotto.exception.ErrorMessage.SIZE_ERROR;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.LottoException;

public class Lotto {

    private final int MIN_NUMBER = 1;
    private final int MAX_NUMBER = 45;
    private final int LOTTO_SIZE = 6;
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
            throw new LottoException(MUST_NOT_BE_DUPLICATED);
        }
    }

    private void validateLottoSize() {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new LottoException(SIZE_ERROR);
        }
    }

    public int getSize() {
        return lottoNumbers.size();
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }


}
