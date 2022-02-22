package domain;

public class LottoNumber {
    private int lottoNumber;
    
    public LottoNumber(int number) {
        checkLottoNumberRange(number);
        this.number = number;
    }

    private void checkLottoNumberRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("1부터 45 사이의 수가 아닙니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LottoNumber that = (LottoNumber)o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
