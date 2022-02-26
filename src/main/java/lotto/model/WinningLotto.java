package lotto.model;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.validator.Validator;

public class WinningLotto {

    private static final String LOTTO_ERROR_MESSAGE = "[ERROR] 잘못된 로또 번호입니다.";
    private static final String BONUS_NUMBER_ERROR_MESSAGE = "[ERROR] 잘못된 보너스 번호입니다.";

    private final List<LottoNumber> winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningLotto(List<LottoNumber> winningNumbers, BonusNumber bonusNumber) {
        validateWinningNumbers(winningNumbers);
        this.winningNumbers = winningNumbers;
        hasDuplicateNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateWinningNumbers(List<LottoNumber> numbers) {
        if (!Validator.isValidLength(numbers) || Validator.isDuplicate(numbers)) {
            throw new RuntimeException(LOTTO_ERROR_MESSAGE);
        }
    }

    public void hasDuplicateNumber(BonusNumber number) {
        if ((winningNumbers.stream()
                .mapToInt(LottoNumber::getLottoNumber)
                .boxed()
                .collect(Collectors.toList()))
                .contains(number.getLottoNumber())) {
            throw new RuntimeException(BONUS_NUMBER_ERROR_MESSAGE);
        }
    }

    public void checkRank(Lottos lottos) {
        lottos.calculateRanks(winningNumbers, bonusNumber);
    }
}
