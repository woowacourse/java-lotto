package domain.lotto;

public class BonusNumber {
    private final LottoNumber lottoNumber;

    private BonusNumber(final LottoNumber lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static BonusNumber of(final LottoNumber lottoNumber) {
        return new BonusNumber(lottoNumber);
    }


    public static BonusNumber of(final int value) {
        return new BonusNumber(new LottoNumber(value));
    }

    public boolean isSameNumber(final LottoNumber lottoNumber) {
        return this.lottoNumber.equals(lottoNumber);
    }
}
