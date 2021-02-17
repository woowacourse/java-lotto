package lotto;

import java.util.Objects;

public class LottoWinner {

    private static final String DUPLICATE_ERROR_MESSAGE = "당첨번호에 이미 있는 보너스 숫자입니다.";

    private final LottoWinnerTicket lottoWinnerTicket;
    private final LottoWinnerBonusNumber lottoWinnerBonusNumber;

    public LottoWinner(LottoWinnerTicket lottoWinnerTicket, LottoWinnerBonusNumber lottoWinnerBonusNumber) {
        this.lottoWinnerTicket = lottoWinnerTicket;
        validateIfWinnerBonusNumberInWinnerTicket(lottoWinnerTicket, lottoWinnerBonusNumber);
        this.lottoWinnerBonusNumber = lottoWinnerBonusNumber;
    }

    public void validateIfWinnerBonusNumberInWinnerTicket
            (LottoWinnerTicket lottoWinnerTicket, LottoWinnerBonusNumber lottoWinnerBonusNumber) {
        if (lottoWinnerTicket.getLottoNumbers().contains(lottoWinnerBonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoWinner that = (LottoWinner) o;
        return Objects.equals(lottoWinnerTicket, that.lottoWinnerTicket) &&
                Objects.equals(lottoWinnerBonusNumber, that.lottoWinnerBonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoWinnerTicket, lottoWinnerBonusNumber);
    }
}
