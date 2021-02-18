package view.dto;

import domain.ball.LottoBall;

import java.util.List;

public class LottoNumbersDto {
    private final List<LottoBall> lottoBalls;

    public LottoNumbersDto(List<LottoBall> lottoBalls) {
        this.lottoBalls = lottoBalls;
    }

    public List<LottoBall> getLottoNumbers() {
        return lottoBalls;
    }
}
