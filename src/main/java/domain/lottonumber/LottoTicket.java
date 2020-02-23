package domain.lottonumber;

public class LottoTicket {

    private final LottoNumbers lottoNumbers;

    public LottoTicket(LottoNumbersDto lottoNumbersDto) {
        this.lottoNumbers = lottoNumbersDto.getLottoNumbers();
    }

    public boolean contains(LottoNumber number) {
        return this.lottoNumbers.contains(number);
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }
}