package lotto.lottoticket;

import java.util.Objects;

import static lotto.lottoticket.TicketValidation.ERROR_MESSAGE_DUPLICATED;

public class BonusBall {
    private final int bonusBall;

    public BonusBall(String value, WinnerTicket winnerTicket) {
        int number = TicketValidation.validateNumber(value);
        TicketValidation.validateNumberInRange(number);
        if (winnerTicket.containsSameNumber(number)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_DUPLICATED);
        }
        this.bonusBall = number;
    }

    public boolean isSameThan(int number) {
        return this.bonusBall == number;
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
