package domain.numberscontainer;

import java.util.Set;
import java.util.stream.Collectors;

public class Ticket extends LottoNumbers {

    private static final String TICKET_DELIMITER = ", ";
    private static final String START_MARK = "[";
    private static final String END_MARK = "]";

//    private final LottoNumbers lottoNumbers;

    public Ticket(String lottoNumbers) {
//        this.lottoNumbers = lottoNumbers;
        super(lottoNumbers);
    }

    public Ticket(Set<LottoNumber> lottoNumbers) {
        super(lottoNumbers);
    }

    public boolean contains(LottoNumber number) {
        return this.lottoNumbers.contains(number);
    }

    @Override
    public String toString() {
        return this.lottoNumbers.stream()
                .map(lottoNumber -> Integer.toString(lottoNumber.getValue()))
                .collect(Collectors.joining(TICKET_DELIMITER, START_MARK, END_MARK));
    }
}