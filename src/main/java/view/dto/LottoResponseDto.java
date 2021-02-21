package view.dto;

import domain.lotto.LottoTicket;

public class LottoResponseDto {
    private final LottoBallsDto lottoBallsDto;

    public LottoResponseDto(LottoTicket lottoTicket) {
        this.lottoBallsDto = new LottoBallsDto(lottoTicket.findLottoNumbers());
    }

    public LottoBallsDto getLottoNumbersDto() {
        return lottoBallsDto;
    }
}
