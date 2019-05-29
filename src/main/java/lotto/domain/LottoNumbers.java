package lotto.domain;

import lotto.Exception.DuplicateLottoNumberException;
import lotto.Exception.InconsistencyLottoNumberException;

import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {
    private static final int LOTTO_NUMBER_SIZE = 45;
    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new InconsistencyLottoNumberException("로또 숫자 생성오류 : 숫자가 45개가 아닙니다.");
        }

        if (isDuplicateLottoNumbers(lottoNumbers)) {
            throw new DuplicateLottoNumberException("로또 숫자 생성오류 : 중복된 숫자가 존재합니다.");
        }

        this.lottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::clone)
                .collect(Collectors.toList());
    }

    private boolean isDuplicateLottoNumbers(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream().distinct().collect(Collectors.toList()).size() != LOTTO_NUMBER_SIZE;
    }
}
