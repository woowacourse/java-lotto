package lotto.domain;

public class LottoNumber {

    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validateBonus(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validateBonus(int bonus) {
        if (bonus < 1 || bonus > 45) {
            throw new IllegalArgumentException("1~45 사이의 숫자여야 합니다.");
        }
    }

    public int getBonus() {
        return lottoNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNumber)) return false;

        LottoNumber that = (LottoNumber) o;

        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return lottoNumber;
    }
}
