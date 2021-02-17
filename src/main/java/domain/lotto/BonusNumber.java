package domain.lotto;

public class BonusNumber {
    private final LottoNumber lottoNumber;

    public BonusNumber(final LottoNumber lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public boolean isSameNumber(final LottoNumber lottoNumber) {
        return this.lottoNumber.equals(lottoNumber);
    }
}
