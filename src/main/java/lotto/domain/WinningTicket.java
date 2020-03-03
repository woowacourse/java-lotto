package lotto.domain;

import java.util.List;

public class WinningTicket extends LottoTicket {
    private static final String DUPLICATE_LOTTO_BALL = "로또볼을 중복입력 하였습니다. 재입력 해주세요.";
    private final LottoBall bonusBall;

    public WinningTicket(List<LottoBall> lottoTicket, LottoBall bonusBall) {
        super(lottoTicket);
        validateDuplicateNumber(lottoTicket,bonusBall);
        this.bonusBall = bonusBall;
    }

    public long hitLottoBall(LottoTicket lottoTicket) {
        return lottoTicket.getLottoTicket()
                .stream()
                .filter(this.lottoTicket::contains)
                .count();
    }

    public boolean hitBonusBall(LottoTicket lottoTicket) {
        return lottoTicket.getLottoTicket()
                .stream()
                .anyMatch(lottoBall ->
                        lottoBall.getLottoBall() == this.bonusBall.getLottoBall());
    }

    private void validateDuplicateNumber(List<LottoBall> lottoTicket, LottoBall bonusBall) {
        if (lottoTicket.contains(bonusBall)){
            throw new IllegalArgumentException(DUPLICATE_LOTTO_BALL);
        }
    }
}
