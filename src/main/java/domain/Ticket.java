package domain;

public class Ticket extends LottoNumbersContainer {
    public Ticket(LottoNumbersDto lottoNumbersDto) {
        super(lottoNumbersDto);
    }

    public boolean contains(LottoNumber number) {
        return this.lottoSixNumbers.contains(number);
    }
}