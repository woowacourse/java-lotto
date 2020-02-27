package domain.numberscontainer;

import java.util.LinkedHashSet;
import java.util.Set;

public class LottoNumbers {
    private static final int SIZE = 6;

    protected final LinkedHashSet<LottoNumber> lottoNumbers;

    public LottoNumbers(LottoNumbersDto lottoNumbersDto) {
        validateSize(lottoNumbersDto.getLottoNumbers());
        this.lottoNumbers = lottoNumbersDto.getLottoNumbers();
    }

    protected void validateSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != SIZE) {
            throw new IllegalArgumentException(String.format("%d개의 숫자를 입력해주세요.", SIZE));
        }
    }
}