package domain.ticket;

import domain.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public class WinningTicket extends Ticket {
    public WinningTicket(List<Integer> fullNumbers) {
        List<Integer> numbers = generateNumbers(fullNumbers);
        validate(numbers);

        this.lottoNumbers.addAll(
                numbers.stream()
                        .sorted()
                        .map(LottoNumber::valueOf)
                        .collect(Collectors.toList())
        );
    }

    @Override
    protected List<Integer> generateNumbers(final List<Integer> fullNumbers) {
        return fullNumbers;
    }
}
