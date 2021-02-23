package lotto.ticket;

import java.util.Objects;

public class BonusBall {
    private final Number bonusBall;

    public BonusBall(String value, WinnerTicket winnerTicket) {
        Number number = Number.valueOf(value);
        TicketValidation.validateSameNumber(number, winnerTicket);
        this.bonusBall = number;
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
