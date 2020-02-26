package domain.lottonumbers;

import domain.lottonumbers.lottonumber.LottoNumber;

public class LottoNumbersDto {

    private static final int DEFAULT_BONUS_NUMBER = 1;

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public LottoNumbersDto(LottoNumbers lottoNumbers) {
        this(lottoNumbers, LottoNumber.of(DEFAULT_BONUS_NUMBER));
    }

    public LottoNumbersDto(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}