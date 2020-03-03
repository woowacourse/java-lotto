package lotto.domain;

public class WinningTicket {
    private static final String DUPLICATE_LOTTO_BALL = "로또볼을 중복입력 하였습니다. 재입력 해주세요.";

    private final LottoBall bonusBall;
    private final LottoTicket winningTicket;

    public WinningTicket(LottoTicket lottoTicket, LottoBall bonusBall) {
        validateDuplicateNumber(lottoTicket, bonusBall);
        this.winningTicket = lottoTicket;
        this.bonusBall = bonusBall;
    }

    public long hitLottoBall(LottoTicket lottoTicket) {
        return lottoTicket.getLottoTicket()
                .stream()
                .filter(this.winningTicket.getLottoTicket()::contains)
                .count();
    }

    public boolean hitBonusBall(LottoTicket lottoTicket) {
        return lottoTicket.getLottoTicket()
                .stream()
                .anyMatch(lottoBall ->
                        lottoBall.getLottoBall() == this.bonusBall.getLottoBall());
    }


    private void validateDuplicateNumber(LottoTicket lottoTicket, LottoBall bonusBall) {
        if (lottoTicket.getLottoTicket().contains(bonusBall)) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_BALL);
        }
    }
}
