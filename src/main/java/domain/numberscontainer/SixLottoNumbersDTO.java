package domain.numberscontainer;

import java.util.Set;

public class SixLottoNumbersDTO {
    private final Set<LottoNumber> sixNumbers;

    public SixLottoNumbersDTO(Set<LottoNumber> sixNumbers) {
        this.sixNumbers = sixNumbers;
    }

    public Set<LottoNumber> getSixNumbers() {
        return sixNumbers;
    }
}