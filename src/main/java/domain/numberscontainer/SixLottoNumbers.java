package domain.numberscontainer;

import java.util.Set;

public class SixLottoNumbers {
    private static final int LOTTO_NUMBERS_SIZE = 6;

    protected final Set<LottoNumber> sixLottoNumbers;

    public SixLottoNumbers(SixLottoNumbersDTO sixLottoNumbersDTO) {
        validateSize(sixLottoNumbersDTO.getSixNumbers());
        this.sixLottoNumbers = sixLottoNumbersDTO.getSixNumbers();
    }

    protected void validateSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(String.format("%d개의 숫자를 입력해주세요.", LOTTO_NUMBERS_SIZE));
        }
    }
}