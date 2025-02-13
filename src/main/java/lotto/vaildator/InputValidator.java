package lotto.vaildator;

import java.util.List;
import lotto.constant.ExceptionMessage;
import lotto.domain.Lotto;

public class InputValidator {

    public static void validateBlank(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getContent());
        }
    }

    public static void validateNumberFormat(String numericContent) {
        try {
            Integer.parseInt(numericContent);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NUMBER_FORMAT.getContent());
        }
    }

    public static void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount % Lotto.LOTTO_PRICE != 0 || purchaseAmount <= 0) {
            String messageTemplate = ExceptionMessage.INVALID_PURCHASE_AMOUNT.getContent();
            String exceptionMessage = String.format(messageTemplate, Lotto.LOTTO_PRICE);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    public static void validateWinningNumbers(List<String> content) {
        try {
            List<Integer> numbers = content.stream().map(Integer::parseInt).toList();
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INPUT.getContent());
        }
    }

    public static void validateBonusNumber(Lotto winningLotto, int bonusNumber) {
        if (winningLotto.hasNumber(bonusNumber)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_NUMBERS.getContent());
        }
    }
}
