package domain;

public class LottoNumber {
    private int lottoNumber;
    
    public LottoNumber(int lottoNumber) {
        checkLottoNumberRange(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void checkLottoNumberRange(int lottoNumber) {
        if (lottoNumber < 1 || lottoNumber > 45) {
            throw new IllegalArgumentException("1부터 45 사이의 수가 아닙니다.");
        }
    }
}
