package lotto.domain.lottos.winnerlotto;

import lotto.domain.lottos.LottoTicket;

import java.util.Objects;

public class LottoWinner {

    private static final String DUPLICATE_ERROR_MESSAGE = "당첨번호에 이미 있는 보너스 숫자입니다.";
    private static final String NULL_ERROR_MESSAGE = "null 값은 허용하지 않습니다.";

    private final LottoTicket lottoWinnerTicket;
    private final LottoBonusNumber lottoBonusNumber;

    public LottoWinner(LottoTicket lottoWinnerTicket, LottoBonusNumber lottoBonusNumber) {
        validateNull(lottoWinnerTicket, lottoBonusNumber);
        this.lottoWinnerTicket = lottoWinnerTicket;
        validateDuplicateNumber(lottoWinnerTicket, lottoBonusNumber);
        this.lottoBonusNumber = lottoBonusNumber;
    }

    public void validateDuplicateNumber
            (LottoTicket lottoWinnerTicket, LottoBonusNumber lottoBonusNumber) {
        if (lottoWinnerTicket.isContain(lottoBonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
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
