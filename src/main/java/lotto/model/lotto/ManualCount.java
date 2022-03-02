package lotto.model.lotto;

import lotto.model.message.LottoManualCountExceptionMessage;
import lotto.utils.ConverterUtils;
import lotto.utils.InputValidateUtils;

public class ManualCount {
    public static final int END = 0;
    private int number;

    public ManualCount(String number) {
        InputValidateUtils.inputBlank(number, LottoManualCountExceptionMessage.BLANK_ERROR.getMassage());
        InputValidateUtils.inputNumber(number, LottoManualCountExceptionMessage.NUMBER_ERROR.getMassage());
        this.number = ConverterUtils.convertStringToInt(number);
    }

    public void createManualLotto() {
        this.number --;
    }

    public boolean isEnd() {
        return number == END;
    }

    public int getNumber() {
        return number;
    }
}
