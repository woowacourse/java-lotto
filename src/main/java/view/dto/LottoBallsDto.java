package view.dto;

import domain.ball.LottoBall;

import java.util.List;

public class LottoBallsDto {
    private final List<LottoBall> lottoBalls;

    public LottoBallsDto(List<LottoBall> lottoBalls) {
        this.lottoBalls = lottoBalls;
    }

    public List<LottoBall> getLottoBalls() {
        return lottoBalls;
    }
}
