package domain;

public class LottoNumber {
    private int lottoNumber;

    public LottoNumber(int number) {
        validateLottoNumberRange(number);
        this.lottoNumber = number;
    }

    private void validateLottoNumberRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("범위를 벗어나는 로또 번호 입니다.");
        }
    }
}
