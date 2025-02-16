package view;

import static domain.Lotto.LOTTO_PRICE;
import static domain.Lotto.MAX_LOTTO_NUMBER;
import static domain.Lotto.MIN_LOTTO_NUMBER;

import domain.ErrorCode;
import domain.Lotto;
import domain.LottoNumber;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputConverter {

    private static final String WINNING_NUMBER_REGEX = "([0-9]+)(,( )*[0-9]+)*";
    private static final String NUMBER_REGEX = "[0-9]+";
    private static final String WINNING_NUMBER_DELIMITER = ",";

    public static Lotto convertToWinningLotto(String input) {
        Matcher matcher = Pattern.compile(WINNING_NUMBER_REGEX).matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(ErrorCode.WINNING_NUMBER_WRONG_SYNTAX.getMessage());
        }

        List<LottoNumber> winningLottoNumbers = Arrays.stream(input.split(WINNING_NUMBER_DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .peek(InputConverter::validateNumberRange)
                .map(LottoNumber::of)
                .toList();

        validateUniqueNumber(winningLottoNumbers);

        return Lotto.issueByNumbers(winningLottoNumbers);
    }

    public static int convertToBonusNumber(String input) {
        Matcher matcher = Pattern.compile(NUMBER_REGEX).matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(ErrorCode.BONUS_NUMBER_WRONG_SYNTAX.getMessage());
        }

        int bonusNumber = Integer.parseInt(input);
        validateNumberRange(bonusNumber);

        return bonusNumber;
    }

    public static int convertPurchaseAmount(String input) {
        Matcher matcher = Pattern.compile(NUMBER_REGEX).matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(ErrorCode.PURCHASE_AMOUNT_WRONG_SYNTAX.getMessage());
        }

        int purchaseAmount = Integer.parseInt(input);
        validatePurchaseAmountRange(purchaseAmount);
        validatePurchaseAmountIsDividedByUnit(purchaseAmount);

        return purchaseAmount;
    }

    private static void validatePurchaseAmountIsDividedByUnit(int value) {
        if (value % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorCode.PURCHASE_AMOUNT_NOT_DIVIDED_BY_UNIT.getMessage());
        }
    }

    private static void validatePurchaseAmountRange(int value) {
        if (value < LOTTO_PRICE) {
            throw new IllegalArgumentException(ErrorCode.PURCHASE_AMOUNT_NOT_IN_RANGE.getMessage());
        }
    }

    private static void validateNumberRange(int value) {
        if (value < MIN_LOTTO_NUMBER || value > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ErrorCode.WINNING_NUMBER_NOT_IN_RANGE.getMessage());
        }
    }

    private static void validateUniqueNumber(List<LottoNumber> values) {
        values.forEach(value -> {
            if (Collections.frequency(values, value) > 1) {
                throw new IllegalArgumentException(ErrorCode.WINNING_NUMBER_DUPLICATED.getMessage());
            }
        });
    }
}
