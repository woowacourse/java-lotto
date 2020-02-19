package domain.numberscontainer;

import domain.LottoNumber;

import java.util.stream.Collectors;

public class Ticket extends LottoNumbersContainer {
    public Ticket(LottoNumbersDto lottoNumbersDto) {
        super(lottoNumbersDto);
    }

    public boolean contains(LottoNumber number) {
        return this.sixLottoNumbers.contains(number);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String numbers = this.sixLottoNumbers.stream()
                .map(lottoNumber -> Integer.toString(lottoNumber.getValue()))
                .collect(Collectors.joining(", "));

        stringBuilder.append("[");
        stringBuilder.append(numbers);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}