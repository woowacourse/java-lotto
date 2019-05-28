package lotto.domain;

import lotto.Exception.DuplicateLottoNumberException;

import java.util.Map;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private final Map<Integer, LottoNumber> lottoNumbers;

    public Lotto(Map<Integer, LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE){
            throw new DuplicateLottoNumberException("중복된 숫자는 안됩니다.");
        }
        this.lottoNumbers = lottoNumbers;
    }
}
