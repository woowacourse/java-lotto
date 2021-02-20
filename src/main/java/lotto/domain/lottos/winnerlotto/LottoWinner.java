package lotto.domain.lottos.winnerlotto;

import lotto.domain.lottos.LottoTicket;

import java.util.Objects;

public class LottoWinner {

    public static final String NULL_ERROR_MESSAGE = "null 값은 허용하지 않습니다.";

    private final LottoTicket lottoWinnerTicket;
    private final LottoBonusNumber lottoBonusNumber;

    public LottoWinner(LottoTicket lottoWinnerTicket, LottoBonusNumber lottoBonusNumber) {
        validateNull(lottoWinnerTicket, lottoBonusNumber);
        this.lottoWinnerTicket = lottoWinnerTicket;
        this.lottoBonusNumber = lottoBonusNumber;
    }

    private void validateNull(LottoTicket lottoWinnerTicket, LottoBonusNumber lottoBonusNumber) {
        Objects.requireNonNull(lottoWinnerTicket, NULL_ERROR_MESSAGE);
        Objects.requireNonNull(lottoBonusNumber, NULL_ERROR_MESSAGE);
    }

    public LottoTicket getLottoWinnerTicket() {
        return lottoWinnerTicket;
    }

    public LottoBonusNumber getLottoWinnerBonusNumber() {
        return lottoBonusNumber;
    }
}
