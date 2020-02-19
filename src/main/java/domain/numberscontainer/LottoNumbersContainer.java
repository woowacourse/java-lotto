package domain.numberscontainer;

import domain.LottoNumber;

import java.util.Set;

abstract class LottoNumbersContainer {
    private static final int TICKET_SIZE = 6;

    protected final Set<LottoNumber> sixLottoNumbers;

    public LottoNumbersContainer(LottoNumbersDto lottoNumbersDto) {
        validateSize(lottoNumbersDto.getSixNumbers());
        this.sixLottoNumbers = lottoNumbersDto.getSixNumbers();
    }

    protected void validateSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != TICKET_SIZE) {
            throw new IllegalArgumentException("6개의 숫자를 입력해주세요.");
        }
    }
}
