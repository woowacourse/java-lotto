package lotto.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.Consumer;
import verus.view.InputTemplate;
import verus.view.StringFormatValidator;

public class InputView {

    private static final StringFormatValidator MONEY_VALIDATOR = StringFormatValidatorFactory
        .moneyValidator();
    private static final StringFormatValidator LOTTO_VALIDATOR = StringFormatValidatorFactory
        .lottoValidator();
    private static final StringFormatValidator NUMBER_VALIDATOR = StringFormatValidatorFactory
        .numberValidator();
    private final InputTemplate inputTemplate;

    public InputView(InputStream inputStream, OutputStream outputStream) {
        inputTemplate = new InputTemplate(inputStream, outputStream);
    }

    public String inputMoneyText(Consumer<Exception> exceptionHandler) {
        return inputTemplate.repeatablyInput("구입금액을 입력해 주세요.", MONEY_VALIDATOR::validate,
            exceptionHandler);
    }

    public String inputLottoText(Consumer<Exception> exceptionHandler) {
        return inputTemplate.repeatablyInput("지난 주 당첨 번호를 입력해 주세요.", LOTTO_VALIDATOR::validate,
            exceptionHandler);
    }

    public String inputBonusText(Consumer<Exception> exceptionHandler) {
        return inputTemplate.repeatablyInput("보너스 볼을 입력해 주세요.", NUMBER_VALIDATOR::validate,
            exceptionHandler);
    }

}
