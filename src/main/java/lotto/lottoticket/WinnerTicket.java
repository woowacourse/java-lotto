package lotto.lottoticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WinnerTicket {
    private static final String COMMA_DELIMITER = ",";

    private final List<LottoNumber> winnerTicket;

    public WinnerTicket(String numbers) {
        this.winnerTicket = splitNumbers(numbers);
    }

    private List<LottoNumber> splitNumbers(String values) {
        List<LottoNumber> numbers = new ArrayList<>();
        for (String value : values.split(COMMA_DELIMITER)) {
            numbers.add(makeValidatedNumber(value));
        }
        return makeValidatedNumbers(numbers);
    }

    private LottoNumber makeValidatedNumber(String value) {
        LottoTicketValidation.validateNumber(value);
        int number = Integer.parseInt(value.trim());
        return new LottoNumber(number);
    }

    private List<LottoNumber> makeValidatedNumbers(List<LottoNumber> numbers) {
        LottoTicketValidation.validateSize(numbers);
        LottoTicketValidation.validateDuplicated(numbers);
        return numbers;
    }

    public boolean containsSameNumber(LottoNumber number) {
        return this.winnerTicket.contains(number);
    }

    public int findMatchCount(LottoTicket lottoTicket) {
        int matchCount = 0;
        for (LottoNumber number : winnerTicket) {
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