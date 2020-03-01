package domain;

public class LottoGameRepeat {
    public static final String ERROR_NOT_POSITIVE_MESSAGE = "횟수는 0 미만으로 입력될 수 없습니다.";
    public static final String ERROR_OUTBOUND_MESSAGE = "입력은 가능 횟수를 초과할 수 없습니다.";
    private final int repeat;

    public LottoGameRepeat(int repeat) {
        validateRepeatCount(repeat);
        this.repeat = repeat;
    }

    private void validateRepeatCount(int repeat) {
        if (repeat < 0) {
            throw new IllegalArgumentException(ERROR_NOT_POSITIVE_MESSAGE);
        }
    }

    public boolean hasRepeat() {
        return hasRepeat(0);
    }

    public boolean hasRepeat(int count) {
        return count < repeat;
    }


    private void validateSplitValue(int repeat) {
        if (repeat > this.repeat) {
            throw new IllegalArgumentException(ERROR_OUTBOUND_MESSAGE);
        }
    }

    /**
     * splitGame은 LottoGameRepeat 인스턴스를 전달받아,
     * 현재 인스턴스의 반복 횟수에서 잔달받은 인스턴스의 반복 횟수만큼 뺀 값을 구하여
     * 새로운 LottoGameRepeat 인스턴스를 만들어 반환한다.
     * 이를 통해 repeat는 불변성을 유지할 수 있다.
     *
     * @param gameRepeat 연산을 수행할 LottoGameRepeat 인스턴스이다.
     * @return 새롭게 생성된 LottoGameRepeat 인스턴스를 반환한다.
     */
    public LottoGameRepeat splitGame(LottoGameRepeat gameRepeat) {
        validateSplitValue(gameRepeat.repeat);
        return new LottoGameRepeat(this.repeat - gameRepeat.repeat);
    }

    @Override
    public String toString() {
        return String.valueOf(repeat);
    }
}