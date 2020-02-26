package domain.numberscontainer;

import java.util.stream.Collectors;

public class Ticket extends LottoNumbers {

    public Ticket(LottoNumbersDto lottoNumbersDto) {
        super(lottoNumbersDto);
    }

    public boolean contains(LottoNumber number) {
        return this.lottoNumbers.contains(number);
    }

    @Override
    public String toString() {
        return this.lottoNumbers.stream()
                .map(lottoNumber -> Integer.toString(lottoNumber.getValue()))
                .collect(Collectors.joining(", ", "[", "]"));
    }
}