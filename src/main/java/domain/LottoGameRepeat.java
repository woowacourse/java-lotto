package domain;

public class LottoGameRepeat {
    public static final String ERROR_NOT_POSITIVE_MESSAGE = "횟수는 0 미만으로 입력될 수 없습니다.";
    public static final String ERROR_OUTBOUND_MESSAGE = "입력은 가능 횟수를 초과할 수 없습니다.";
    private int repeat;

    public LottoGameRepeat(int repeat) {
        validateRepeatCount(repeat);
        this.repeat = repeat;
    }

    private void validateRepeatCount(int repeat) {
        if (repeat < 0) {
            throw new IllegalArgumentException(ERROR_NOT_POSITIVE_MESSAGE);
        }
    }

    private void validateSplitValue(int repeat) {
        if (repeat > this.repeat) {
            throw new IllegalArgumentException(ERROR_OUTBOUND_MESSAGE);
        }
    }

    public boolean checkRepeatPositive() {
        return repeat > 0;
    }

    public boolean checkLoopTerminate(int count) {
        return count < repeat;
    }

    public LottoGameRepeat splitLottoGameRepeat(int repeat) {
        validateRepeatCount(repeat);
        validateSplitValue(repeat);
        this.repeat -= repeat;
        return new LottoGameRepeat(repeat);
    }

    @Override
    public String toString() {
        return String.valueOf(repeat);
    }
}