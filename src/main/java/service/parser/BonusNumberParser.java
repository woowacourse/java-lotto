package service.parser;

import constant.LottoConstants;
import java.util.List;
import validator.Validator;

public class BonusNumberParser {

    public static int parseBonusNumber(List<Integer> winningNumbers, String input) {
        Validator.validateEmptyInput(input);
        Validator.checkInvalidNumberForm(input);

        int bonusNumber = Integer.parseInt(input);
        Validator.checkOutOfRange(bonusNumber, LottoConstants.LOTTO_NUMBER_START, LottoConstants.LOTTO_NUMBER_END, "로또의 숫자가 1~45의 유효 범위를 벗어납니다.");
        validateDuplicateWithBonusNumber(winningNumbers, bonusNumber);

        return bonusNumber;
    }

    private static void validateDuplicateWithBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)){
            throw new IllegalArgumentException("보너스 번호가 당첨 번호와 같습니다.");
        }
    }
}
