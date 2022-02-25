package lotto.model;

import java.util.List;
import lotto.model.validator.Validator;

public class WinningLotto {

    private static final String LOTTO_ERROR_MESSAGE = "[ERROR] 잘못된 로또 번호입니다.";

    private final List<Integer> winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, BonusNumber bonusNumber) {
        validateWinningNumbers(winningNumbers);
        bonusNumber.hasDuplicateNumber(winningNumbers);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateWinningNumbers(List<Integer> numbers) {
        if (!Validator.isValidLength(numbers) || !Validator.isValidRange(numbers) || Validator.isDuplicate(numbers)) {
            throw new RuntimeException(LOTTO_ERROR_MESSAGE);
        }
    }

    public void checkRank(Lottos lottos) {
        lottos.calculateRanks(winningNumbers, bonusNumber);
    }
}
