package lotto.view;

public class ErrorView {

    private ErrorView() {
    }

    public static void printErrorMessage(final Exception exception) {
        System.out.println(exception.getMessage());
    }
}
