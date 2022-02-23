package model;

import exception.DuplicatedLottoNumbersException;

public class WinningLottoNumbers {
    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningLottoNumbers(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new DuplicatedLottoNumbersException();
        }
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }
}
