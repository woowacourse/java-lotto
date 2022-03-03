package exception.lotto;

public class LottosNullException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "lottos 리스트에는 1개 이상의 lotto가 포함되어야 한다.";

    public LottosNullException() {
        super(ERROR_MESSAGE);
    }
}