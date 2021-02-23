package lotto.ticket;

import java.util.Objects;

import static lotto.ticket.Ticket.ERROR_MESSAGE_DUPLICATED;

public class BonusBall {
    private final Number bonusBall;

    public BonusBall(String value, WinnerTicket winnerTicket) {
        Number number = Number.valueOf(value);
        validateSameNumber(number, winnerTicket);
        this.bonusBall = number;
    }

    private void validateSameNumber(Number number, WinnerTicket winnerTicket) {
        if (winnerTicket.isSameNumber(number)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_DUPLICATED);
        }
    }

    public boolean isSameThan(Number number) {
        return bonusBall.equals(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusBall ball = (BonusBall) o;
        return Objects.equals(bonusBall, ball.bonusBall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusBall);
    }
}
