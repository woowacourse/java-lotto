package domain;

import java.util.Set;

public class LottoNumbersDto {
    private final Set<LottoNumber> sixNumbers;
    private final LottoNumber bonusNumber;

    public LottoNumbersDto(Set<LottoNumber> sixNumbers) {
        this(sixNumbers, LottoNumber.ERROR);
    }

    public LottoNumbersDto(Set<LottoNumber> sixNumbers, LottoNumber bonusNumber) {
        this.sixNumbers = sixNumbers;
        this.bonusNumber = bonusNumber;
    }

    public Set<LottoNumber> getSixNumbers() {
        return sixNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
