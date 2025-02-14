package lotto.domain;

import static lotto.exception.ErrorMessage.MUST_NOT_BE_DUPLICATED;
import static lotto.exception.ErrorMessage.SIZE_ERROR;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.LottoException;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
        validateLottoSize();
        validateDuplicateNumber();
    }

    private void validateDuplicateNumber() {
        Set<LottoNumber> set = new HashSet<>(lottoNumbers);
        if (set.size() != LOTTO_SIZE) {
            throw new LottoException(MUST_NOT_BE_DUPLICATED);
        }
    }

    private void validateLottoSize() {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new LottoException(SIZE_ERROR);
        }
    }

    public int checkMatchCount(Lotto currentLotto) {

        int count = 0;
        for (LottoNumber lottoNumber : lottoNumbers) {
            if (currentLotto.hasNumber(lottoNumber)) {
                count += 1;
            }
        }
        return count;
    }

    public boolean hasNumber(LottoNumber currentLottoNumber) {
        for (LottoNumber lottoNumber : lottoNumbers) {
            if (lottoNumber.equals(currentLottoNumber)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBonus(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    public int getSize() {
        return lottoNumbers.size();
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }


}
