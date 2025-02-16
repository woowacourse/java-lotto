package lotto.domain;

import lotto.constant.LottoConstants;
import lotto.util.Parser;

public class WinningLotto {

    private static final String RANGE_ERROR = "1과 45 사이의 수를 입력하셔야 합니다.";
    private static final String BONUS_NUMBER_FORMAT_ERROR = "보너스 숫자는 숫자여야 합니다.";
    private static final String BONUS_NUMBER_DUPLICATED_ERROR = "보너스 숫자는 당첨 번호와 중복될 수 없습니다.";

    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(Lotto lotto, String bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = validate(bonusNumber);
    }

    private int validate(String bonusNumber) {
        int parsedBonusNumber = Parser.validateNumber(bonusNumber, BONUS_NUMBER_FORMAT_ERROR);
        checkRange(parsedBonusNumber);
        validateBonusNumber(lotto, parsedBonusNumber);
        return parsedBonusNumber;
    }

    private void checkRange(int number) {
        if (number < LottoConstants.LOTTO_MINIMUM_NUMBER.getNumber() || number > LottoConstants.LOTTO_MAXIMUM_NUMBER.getNumber()) {
            throw new IllegalArgumentException(RANGE_ERROR);
        }
    }

    private void validateBonusNumber(Lotto lotto, int bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATED_ERROR);
        }
    }

    public Lotto getLotto() {
        return lotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
