package lotto.view;

import static lotto.view.InputTemplate.input;
import static lotto.view.StringFormatValidator.lottoValidator;
import static lotto.view.StringFormatValidator.moneyValidator;
import static lotto.view.StringFormatValidator.numberValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class InputView {

    private InputView() {
    }

    public static String inputMoneyText(Consumer<Exception> exceptionHandler) {
        return input("구입금액을 입력해 주세요.", moneyValidator(), exceptionHandler,
            () -> inputMoneyText(exceptionHandler));
    }

    public static List<String> inputManualLottoesText(Consumer<Exception> exceptionHandler) {
        String text = inputManualLottoSizeText(exceptionHandler);
        int size = Integer.parseInt(text);
        return inputManualLottoesText(exceptionHandler, size);
    }

    private static String inputManualLottoSizeText(Consumer<Exception> exceptionHandler) {
        return input("수동으로 구매할 로또 수를 입력해 주세요.", numberValidator(), exceptionHandler,
            () -> inputManualLottoSizeText(exceptionHandler));
    }

    private static List<String> inputManualLottoesText(Consumer<Exception> exceptionHandler,
        int size) {
        if (size == 0) {
            return Collections.emptyList();
        }
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String> manualLottoesText = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            manualLottoesText.add(inputManualLottoText(exceptionHandler));
        }
        return manualLottoesText;
    }

    private static String inputManualLottoText(Consumer<Exception> exceptionHandler) {
        return input(lottoValidator(), exceptionHandler,
            () -> inputManualLottoText(exceptionHandler));
    }

    public static String inputWinnerLottoText(Consumer<Exception> exceptionHandler) {
        return input("지난 주 당첨 번호를 입력해 주세요.", lottoValidator(), exceptionHandler,
            () -> inputWinnerLottoText(exceptionHandler));
    }

    public static String inputBonusText(Consumer<Exception> exceptionHandler) {
        return input("보너스 볼을 입력해 주세요.", numberValidator(), exceptionHandler,
            () -> inputBonusText(exceptionHandler));
    }

}
