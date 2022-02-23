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

    public LottoRank getRankBy(LottoNumbers lottoNumbers) {
        int count = getMatchedCountAboutWinningNumbers(lottoNumbers);
        boolean bonusMatch = isBonusMatch(lottoNumbers);
        return LottoRank.of(count, bonusMatch);
    }

    private int getMatchedCountAboutWinningNumbers(LottoNumbers lottoNumbers) {
        return this.lottoNumbers.getMatchedNumberCountWith(lottoNumbers);
    }

    private boolean isBonusMatch(LottoNumbers lottoNumbers) {
        return lottoNumbers.contains(bonusNumber);
    }
}
