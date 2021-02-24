package lotto.lottoticket;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WinnerTicket {
    private static final String COMMA_DELIMITER = ",";

    private final List<LottoNumber> winnerTicket;

    public WinnerTicket(String numbers) {
        this.winnerTicket = splitNumbers(numbers);
    }

    private List<LottoNumber> splitNumbers(String values) {
        return Arrays.stream(values.split(COMMA_DELIMITER))
                .map(this::makeValidatedNumber)
                .collect(Collectors.collectingAndThen(Collectors.toList(), this::makeValidatedNumbers));
    }

    private LottoNumber makeValidatedNumber(String value) {
        return LottoNumber.valueOf(value);
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