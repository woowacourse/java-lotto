package lotto.domain;

import static lotto.exception.ErrorMessage.SIZE_ERROR;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.LottoException;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private final Set<LottoNumber> lottoNumbers;

    public Lotto(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new HashSet<>(lottoNumbers);
        validateLottoSize();
    }

    private void validateLottoSize() {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new LottoException(SIZE_ERROR);
        }
    }

    public int checkMatchCount(Lotto currentLotto) {
        return (int) lottoNumbers.stream()
                .filter(currentLotto::hasNumber)
                .count();
    }

    public boolean hasNumber(LottoNumber currentLottoNumber) {
        return lottoNumbers.contains(currentLottoNumber);
    }

    public boolean checkBonus(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    public int getSize() {
        return lottoNumbers.size();
    }

    public List<Integer> getLotto() {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .toList();
    }

}
