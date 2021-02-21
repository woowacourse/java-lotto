package lotto.lottoticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WinnerTicket {
    private static final String COMMA_DELIMITER = ",";

    private final List<Integer> winnerTicket;

    public WinnerTicket(String numbers) {
        this.winnerTicket = splitNumbers(numbers);
    }

    private List<Integer> splitNumbers(String values) {
        List<Integer> numbers = new ArrayList<>();
        for (String value : values.split(COMMA_DELIMITER)) {
            numbers.add(makeValidatedNumber(value));
        }
        return makeValidatedNumbers(numbers);
    }

    private int makeValidatedNumber(String value) {
        TicketValidation.validateNumber(value);
        int number = Integer.parseInt(value.trim());
        TicketValidation.validateNumberInRange(number);
        return number;
    }

    private List<Integer> makeValidatedNumbers(List<Integer> numbers) {
        TicketValidation.validateSize(numbers);
        TicketValidation.validateDuplicated(numbers);
        return numbers;
    }

    public boolean containsSameNumber(int number) {
        return this.winnerTicket.contains(number);
    }

    public int findMatchCount(LottoTicket lottoTicket) {
        int matchCount = 0;
        for (Integer number : winnerTicket) {
            matchCount += lottoTicket.countWithContaining(number);
        }
        return matchCount;
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