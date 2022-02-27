package lotto.view;

import static lotto.view.StringFormatValidator.lottoValidator;
import static lotto.view.StringFormatValidator.moneyValidator;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class InputView {

    private static final StringFormatValidator MONEY_VALIDATOR = moneyValidator();
    private static final StringFormatValidator LOTTO_VALIDATOR = lottoValidator();
    private static final StringFormatValidator NUMBER_VALIDATOR = StringFormatValidator
        .numberValidator();
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputMoneyText(Consumer<Exception> exceptionHandler) {
        return inputTemplate("구입금액을 입력해 주세요.", MONEY_VALIDATOR::validate, exceptionHandler,
            () -> inputMoneyText(exceptionHandler));
    }

    private static String inputTemplate(String message, Consumer<String> validator,
        Consumer<Exception> exceptionHandler, Supplier<String> supplier) {
        try {
            String value = inputWithMessage(message);
            validator.accept(value);
            return value;
        } catch (IllegalArgumentException e) {
            exceptionHandler.accept(e);
            return supplier.get();
        }
    }

    public static String inputLottoText(Consumer<Exception> exceptionHandler) {
        return inputTemplate("지난 주 당첨 번호를 입력해 주세요.", LOTTO_VALIDATOR::validate, exceptionHandler,
            () -> inputLottoText(exceptionHandler));
    }

    public static String inputBonusText(Consumer<Exception> exceptionHandler) {
        return inputTemplate("보너스 볼을 입력해 주세요.", NUMBER_VALIDATOR::validate, exceptionHandler,
            () -> inputBonusText(exceptionHandler));
    }

    private static String inputWithMessage(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

}
