package lotto.view;

public class ErrorView {
    public static final String ERROR_PREFIX = "[ERROR] ";

    private ErrorView() {

    }

    public static void printIllegalMoneyErrorMessage() {
        System.out.println(ERROR_PREFIX + "구입 금액을 잘못 입력 하셨습니다.");
    }

    public static void printIllegalWinningLottoMessage() {
        System.out.println(ERROR_PREFIX + "당첨 번호와 보너스 번호가 중복됩니다.");
    }

    public static void printIllegalLottoNumberMessage() {
        System.out.println(ERROR_PREFIX + "로또 번호를 잘못 입력 하셨습니다.");
    }

    public static void printIllegalLottoNumbersMessage() {
        System.out.println(ERROR_PREFIX + "로또 티켓 내 번호들을 잘못 입력하셨습니다.");
    }
}
