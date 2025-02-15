package domain;

public class LottoTicket {
    public static int LOTTO_PRICE = 1000;

    private final LottoNumbers lottoNumbers;

    public LottoTicket(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public int getSize() {
        return lottoNumbers.getSize();
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }
}
