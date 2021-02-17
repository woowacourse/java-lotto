package lotto.lottoticket;

import java.util.Objects;

public class BonusBall {
    private final int bonusBall;

    public BonusBall(String value, WinnerTicket winnerTicket){
        int number = TicketValidation.validateNumber(value);
        TicketValidation.validateNumberInRange(number);
        if(winnerTicket.isSameNumber(number)){
            throw new IllegalArgumentException("중복된 숫자가 존재합니다.");
        }
        this.bonusBall = number;
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
