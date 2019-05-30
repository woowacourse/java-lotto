package lotto.view.validator;

import java.util.regex.Pattern;

import static lotto.domain.LottoNumber.LOTTO_LAST_NUMBER;
import static lotto.domain.LottoNumber.LOTTO_START_NUMBER;

public class InputValidator {
    private static final Pattern BUY_PRICE_REGEX = Pattern.compile("^[1-9][0-9]{3,}$");
    private static final Pattern WINNING_NUMBER_REGEX = Pattern.compile("^([0-9]{1,2}, )+[0-9]{1,2}$");

    public static boolean inputValidateBuyPrice(final String buyPrice) {
        return BUY_PRICE_REGEX.matcher(buyPrice).matches();
    }

    public static boolean inputValidateWinningNumber(final String winningNumbers) {
        return WINNING_NUMBER_REGEX.matcher(winningNumbers).matches();
    }

    public static boolean inputValidateBonusBall(final int bonusBall) {
        return LOTTO_START_NUMBER < bonusBall && bonusBall <= LOTTO_LAST_NUMBER;
    }
}
