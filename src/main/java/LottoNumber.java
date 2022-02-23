import exception.InvalidRangeLottoNumberException;

public class LottoNumber {

    public LottoNumber(int number) {
        if (isInvalidRange(number)) {
            throw new InvalidRangeLottoNumberException();
        }
    }

    private boolean isInvalidRange(int number) {
        return 1 > number || number > 45;
    }

}
