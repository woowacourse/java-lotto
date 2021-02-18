package lotto.view;

public class ErrorView {
    public static final String ERROR_PREFIX = "[ERROR] ";

    private ErrorView() {

    }

    public static void printIllegalMoneyErrorMessage() {
        System.out.println(ERROR_PREFIX + "구입 금액을 잘못 입력 하셨습니다.");
    }
}
