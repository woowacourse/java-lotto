package lotto.domain;

import java.util.List;

public class LottoTicket {
    private List<LottoBall> lottoBalls;

    public LottoTicket(List<LottoBall> lottoBalls) {
        this.lottoBalls = lottoBalls;
    }
}
