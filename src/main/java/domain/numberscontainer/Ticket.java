package domain.numberscontainer;

import java.util.Set;
import java.util.stream.Collectors;

public class Ticket {
    private static final String TICKET_DELIMITER = ", ";
    private static final String START_MARK = "[";
    private static final String END_MARK = "]";

    private LottoNumbers lottoNumbers;

    public Ticket(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new LottoNumbers(lottoNumbers);
    }

    public Ticket(String lottoNumbers) {
        this.lottoNumbers = new LottoNumbers(lottoNumbers);
    }

    public boolean contains(LottoNumber number) {
        return this.lottoNumbers.contains(number);
    }

    @Override
    public String toString() {
        return this.lottoNumbers.get().stream()
                .map(lottoNumber -> Integer.toString(lottoNumber.getValue()))
                .collect(Collectors.joining(TICKET_DELIMITER, START_MARK, END_MARK));
    }
}