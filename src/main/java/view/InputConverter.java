package view;

import static domain.Lotto.LOTTO_PRICE;
import static domain.Lotto.MAX_LOTTO_NUMBER;
import static domain.Lotto.MIN_LOTTO_NUMBER;

import domain.Lotto;
import domain.LottoNumber;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputConverter {

    private static final String WINNING_NUMBER_REGEX = "([0-9]+)(,[0-9]+)*";
    private static final String NUMBER_REGEX = "[0-9]+";
    private static final String WINNING_NUMBER_DELIMITER = ",";

    public static Lotto convertToWinningLotto(String input) {
        Matcher matcher = Pattern.compile(WINNING_NUMBER_REGEX).matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("당첨 번호 입력 양식이 올바르지 않습니다.");
        }

        List<LottoNumber> winningLottoNumbers = Arrays.stream(input.split(WINNING_NUMBER_DELIMITER))
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
            throw new IllegalArgumentException("보너스 번호 입력 양식이 올바르지 않습니다.");
        }

        int bonusNumber = Integer.parseInt(input);
        validateNumberRange(bonusNumber);

        return bonusNumber;
    }

    public static int convertPurchaseAmount(String input) {
        Matcher matcher = Pattern.compile(NUMBER_REGEX).matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("구입 금액 입력 양식이 올바르지 않습니다.");
        }

        int purchaseAmount = Integer.parseInt(input);
        validatePurchaseAmountRange(purchaseAmount);
        validatePurchaseAmountIsDividedByUnit(purchaseAmount);

        return purchaseAmount;
    }

    private static void validatePurchaseAmountIsDividedByUnit(int value) {
        if (value % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은" + LOTTO_PRICE + "원 단위로 가능합니다.");
        }
    }

    private static void validatePurchaseAmountRange(int value) {
        if (value < LOTTO_PRICE) {
            throw new IllegalArgumentException("구입 금액은 " + LOTTO_PRICE + "원 이상부터 가능합니다.");
        }
    }

    private static void validateNumberRange(int value) {
        if (value < MIN_LOTTO_NUMBER || value > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("당첨 번호는 " + MIN_LOTTO_NUMBER + " ~ " + MAX_LOTTO_NUMBER + "만 가능합니다.");
        }
    }

    private static void validateUniqueNumber(List<LottoNumber> values) {
        values.forEach(value -> {
            if (Collections.frequency(values, value) > 1) {
                throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
            }
        });
    }
}
