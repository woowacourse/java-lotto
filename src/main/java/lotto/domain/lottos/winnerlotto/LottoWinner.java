package lotto.domain.lottos.winnerlotto;

import lotto.domain.lottos.LottoTicket;

import java.util.Objects;

public class LottoWinner {

    private static final String DUPLICATE_ERROR_MESSAGE = "당첨번호에 이미 있는 보너스 숫자입니다.";
    private static final String NULL_ERROR_MESSAGE = "null 값은 허용하지 않습니다.";

    private final LottoTicket lottoWinnerTicket;
    private final LottoWinnerBonusNumber lottoWinnerBonusNumber;

    public LottoWinner(LottoTicket lottoWinnerTicket, LottoWinnerBonusNumber lottoWinnerBonusNumber) {
        validateNull(lottoWinnerTicket, lottoWinnerBonusNumber);
        this.lottoWinnerTicket = lottoWinnerTicket;
        validateIfWinnerBonusNumberInWinnerTicket(lottoWinnerTicket, lottoWinnerBonusNumber);
        this.lottoWinnerBonusNumber = lottoWinnerBonusNumber;
    }

    public void validateIfWinnerBonusNumberInWinnerTicket
            (LottoTicket lottoWinnerTicket, LottoWinnerBonusNumber lottoWinnerBonusNumber) {
        if (lottoWinnerTicket.isContain(lottoWinnerBonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }

    private void validateNull(LottoTicket lottoWinnerTicket, LottoWinnerBonusNumber lottoWinnerBonusNumber) {
        Objects.requireNonNull(lottoWinnerTicket, NULL_ERROR_MESSAGE);
        Objects.requireNonNull(lottoWinnerBonusNumber, NULL_ERROR_MESSAGE);
    }

    public LottoTicket getLottoWinnerTicket() {
        return lottoWinnerTicket;
    }

    public LottoWinnerBonusNumber getLottoWinnerBonusNumber() {
        return lottoWinnerBonusNumber;
    }
}
