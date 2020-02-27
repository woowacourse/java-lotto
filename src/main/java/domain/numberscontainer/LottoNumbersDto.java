package domain.numberscontainer;

import java.util.LinkedHashSet;
import java.util.Set;

public class LottoNumbersDto {

    private final LinkedHashSet<LottoNumber> lottoNumbers;

    public LottoNumbersDto(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new LinkedHashSet<>(lottoNumbers);
    }

    public LinkedHashSet<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}