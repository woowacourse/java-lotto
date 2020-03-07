package lotto.view;

public class InputViewExceptionHandler {

    public static <T> T handle(InputStrategy<T> inputStrategy) {
        try {
            return inputStrategy.input();
        } catch (IllegalArgumentException e) {
            OutputView.printRetryRequestWithMessage(e.getMessage());
            return handle(inputStrategy);
        }
    }
}
