package Lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final String LOTTO_NUMBER_DUPLICATED_MESSAGE = "잘못된 로또 번호입니다. 중복 안됨, 갯수는 6개";
    private static final int LOTTO_NUMBER_AMOUNT = 6;

    private List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> lottoNumberWithoutDuplication = new HashSet<LottoNumber>(lottoNumbers);
        validate(lottoNumberWithoutDuplication);
        this.lottoNumbers = lottoNumbers;
    }

    private void validate(Set<LottoNumber> lottoNumberWithoutDuplication) {
        if (wrongAmountOfNumbers(lottoNumberWithoutDuplication)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DUPLICATED_MESSAGE);
        }
    }

    private boolean wrongAmountOfNumbers(Set<LottoNumber> lottoNumberWithoutDuplication) {
        return lottoNumberWithoutDuplication.size() != LOTTO_NUMBER_AMOUNT;
    }

    public boolean hasBonusNumber(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    public int countMatchingAmountWith(Lotto inputLotto) {
        return (int)lottoNumbers.stream()
                .filter(inputLotto::contains)
                .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }
}
