package lotto.lottoticket;

import java.util.Objects;

import static lotto.lottoticket.LottoTicketValidation.ERROR_MESSAGE_DUPLICATED;

public class BonusBall {
    private final LottoNumber bonusBall;

    public BonusBall(String value, WinnerTicket winnerTicket) {
        LottoTicketValidation.validateNumber(value);
        this.bonusBall = makeValidateNumber(value, winnerTicket);
    }

    private LottoNumber makeValidateNumber(String value, WinnerTicket winnerTicket) {
        LottoNumber lottoNumber = new LottoNumber(Integer.parseInt(value.trim()));
        if (winnerTicket.containsSameNumber(lottoNumber)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_DUPLICATED);
        }
        return lottoNumber;
    }

    public boolean isSameThan(LottoNumber number) {
        return this.bonusBall.equals(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusBall bonusBall1 = (BonusBall) o;
        return Objects.equals(bonusBall, bonusBall1.bonusBall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusBall);
    }
}
