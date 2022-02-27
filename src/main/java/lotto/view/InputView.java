package lotto.view;

import static lotto.view.StringFormatValidator.lottoValidator;
import static lotto.view.StringFormatValidator.moneyValidator;
import static lotto.view.StringFormatValidator.numberValidator;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputMoneyText(Consumer<Exception> exceptionHandler) {
        return inputTemplate("구입금액을 입력해 주세요.", moneyValidator(), exceptionHandler,
            () -> inputMoneyText(exceptionHandler));
    }

    private static String inputTemplate(String message, StringFormatValidator validator,
        Consumer<Exception> exceptionHandler, Supplier<String> supplier) {
        try {
            String value = inputWithMessage(message);
            validator.validate(value);
            return value;
        } catch (IllegalArgumentException e) {
            exceptionHandler.accept(e);
            return supplier.get();
        }
    }

    private static String inputWithMessage(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public static String inputLottoText(Consumer<Exception> exceptionHandler) {
        return inputTemplate("지난 주 당첨 번호를 입력해 주세요.", lottoValidator(), exceptionHandler,
            () -> inputLottoText(exceptionHandler));
    }

    public static String inputBonusText(Consumer<Exception> exceptionHandler) {
        return inputTemplate("보너스 볼을 입력해 주세요.", numberValidator(), exceptionHandler,
            () -> inputBonusText(exceptionHandler));
    }

}
