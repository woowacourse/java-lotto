package lotto.lottoticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WinnerTicket {
    private final List<Integer> winnerTicket;

    public WinnerTicket(String numbers){
        this.winnerTicket = splitNumbers(numbers);
    }

    private List<Integer> splitNumbers(String values){
        List<Integer> numbers = new ArrayList<>();
        for (String value : values.split(",")){
            value = value.replace(" ", "");
            int number = TicketValidation.validateNumber(value);
            TicketValidation.validateNumberInRange(number);
            numbers.add(number);
        }
        TicketValidation.validateSize(numbers);
        TicketValidation.validateDuplicated(numbers);
        return numbers;
    }

    public boolean isSameNumber(int number) {
        return this.winnerTicket.contains(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinnerTicket that = (WinnerTicket) o;
        return Objects.equals(winnerTicket, that.winnerTicket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winnerTicket);
    }
}