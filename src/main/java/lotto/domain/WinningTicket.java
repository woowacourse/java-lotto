package lotto.domain;

import java.util.Collections;
import java.util.List;

public class WinningTicket {
    private static final String ERROR_ILLEGAL_ARGUMENT_EXCEPTION = "해당 볼이 중복되어 입력됐습니다. 당첨 번호를 다시 입력해주세요.";

    private final List<LottoBall> winningTicket;
    private final LottoBall BonusBall;

    public WinningTicket(List<LottoBall> winningTicket, int BonusBall) {
        this.winningTicket = Collections.unmodifiableList(winningTicket);
        this.BonusBall = LottoBallFactory.findByLottoBall(BonusBall);
        validateWinningBallsWithDuplicatedBonusBall();
    }

    private void validateWinningBallsWithDuplicatedBonusBall() {
        if (winningTicket.contains(this.BonusBall)) {
            throw new IllegalArgumentException(ERROR_ILLEGAL_ARGUMENT_EXCEPTION);
        }
    }

    public int hitLottoBalls(LottoTicket lottoTicket) {
        return (int) lottoTicket.getLottoTicket()
                .stream()
                .filter(winningTicket::contains)
                .count();
    }

    public boolean hitBonusBall(LottoTicket lottoTicket) {
        return lottoTicket.getLottoTicket()
                .contains(BonusBall);
    }
}
