package lotto.view;

import java.util.Scanner;
import java.util.function.Consumer;

public class InputView {

    private static final StringFormatValidator MONEY_VALIDATOR = StringFormatValidator.moneyValidator();
    private static final StringFormatValidator LOTTO_VALIDATOR = StringFormatValidator.lottoValidator();
    private static final StringFormatValidator NUMBER_VALIDATOR = StringFormatValidator.numberValidator();
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {}

    public static String inputMoneyText(Consumer<Exception> exceptionHandler) {
        try {
            String value = inputWithMessage("구입금액을 입력해 주세요.");
            MONEY_VALIDATOR.validate(value);
            return value;
        } catch (IllegalArgumentException e) {
            exceptionHandler.accept(e);
            return inputMoneyText(exceptionHandler);
        }
    }

    public static String inputLottoText(Consumer<Exception> exceptionHandler) {
        try {
            String value = inputWithMessage("지난 주 당첨 번호를 입력해 주세요.");
            LOTTO_VALIDATOR.validate(value);
            return value;
        } catch (IllegalArgumentException e) {
            exceptionHandler.accept(e);
            return inputLottoText(exceptionHandler);
        }
    }

    public static String inputBonusText(Consumer<Exception> exceptionHandler) {
        try {
            String value = inputWithMessage("보너스 볼을 입력해 주세요.");
            NUMBER_VALIDATOR.validate(value);
            return value;
        } catch (IllegalArgumentException e) {
            exceptionHandler.accept(e);
            return inputBonusText(exceptionHandler);
        }
    }

    private static String inputWithMessage(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

}
