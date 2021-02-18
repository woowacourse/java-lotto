package view.dto;

import domain.lotto.Lotto;

public class LottoResponseDto {
    private final LottoNumbersDto lottoNumbersDto;

    public LottoResponseDto(Lotto lotto) {
        this.lottoNumbersDto = new LottoNumbersDto(lotto.findLottoNumbers());
    }

    public LottoNumbersDto getLottoNumbersDto() {
        return lottoNumbersDto;
    }
}
