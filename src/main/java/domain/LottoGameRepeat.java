package domain;

public class LottoGameRepeat {
    public static final String ERROR_NOT_POSITIVE_MESSAGE = "횟수는 0 이하로 입력될 수 없습니다.";
    private int repeat;

    public LottoGameRepeat(int repeat) {
        validateRepeatCount(repeat);
        this.repeat = repeat;
    }

    private void validateRepeatCount(int repeat) {
        if (repeat <= 0) {
            throw new IllegalArgumentException(ERROR_NOT_POSITIVE_MESSAGE);
        }
    }

    public boolean checkLoopTerminate(int count) {
        return count < repeat;
    }

    @Override
    public String toString() {
        return String.valueOf(repeat);
    }
}