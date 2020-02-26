package domain.numberscontainer;

import java.util.stream.Collectors;

public class Ticket extends SixLottoNumbers {

    public Ticket(SixLottoNumbersDTO sixLottoNumbersDTO) {
        super(sixLottoNumbersDTO);
    }

    public boolean contains(LottoNumber number) {
        return this.sixLottoNumbers.contains(number);
    }

    @Override
    public String toString() {
        return this.sixLottoNumbers.stream()
                .map(lottoNumber -> Integer.toString(lottoNumber.getValue()))
                .collect(Collectors.joining(", ", "[", "]"));
    }
}