package lotto.domain;

import java.util.List;

public class WinningTicket extends LottoTicket {
    private final LottoBall bonusBall;

    public WinningTicket(List<LottoBall> lottoTicket, LottoBall bonusBall) {
        super(lottoTicket);
        this.bonusBall = bonusBall;
    }

    public long hitLottoBall(LottoTicket lottoTicket){
        return lottoTicket.getLottoTicket()
                .stream()
                .filter(this.lottoTicket::contains)
                .count();
    }

    public boolean hitBonusBall(LottoTicket lottoTicket){
        return lottoTicket.getLottoTicket()
                .stream()
                .anyMatch(lottoBall ->
                        lottoBall.getLottoBall() == this.bonusBall.getLottoBall());
    }
}
