package view.dto;

import domain.lotto.Lotto;

public class LottoResponseDto {
    private final LottoBallsDto lottoBallsDto;

    public LottoResponseDto(Lotto lotto) {
        this.lottoBallsDto = new LottoBallsDto(lotto.findLottoNumbers());
    }

    public LottoBallsDto getLottoNumbersDto() {
        return lottoBallsDto;
    }
}
