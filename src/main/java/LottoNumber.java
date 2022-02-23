import exception.InvalidRangeLottoNumberException;

public class LottoNumber {
    private int lottoNumber;

    public LottoNumber(int number) {
        if (isInvalidRange(number)) {
            throw new InvalidRangeLottoNumberException();
        }
        this.lottoNumber = number;
    }

    private boolean isInvalidRange(int number) {
        return 1 > number || number > 45;
    }

    public int getLottoNumber() {
        return lottoNumber;
    }
}
