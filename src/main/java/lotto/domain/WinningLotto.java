package lotto.domain;

import lotto.util.Parser;

public class WinningLotto {

    private Lotto lotto;
    private int bonusNumber;

    public WinningLotto(Lotto lotto, String bonusNumber) {
        this.lotto = lotto;
        validate(bonusNumber);
    }

    private void validate(String bonusNumber) {
        int parsedBonusNumber = Parser.validateNumber(bonusNumber, "보너스 숫자는 숫자여야 합니다.");
        checkRange(parsedBonusNumber);
        validateBonusNumber(lotto, parsedBonusNumber);
        this.bonusNumber = parsedBonusNumber;
    }

    private void validateBonusNumber(Lotto lotto, int bonusNumber) {
        lotto.checkDuplicate(bonusNumber);
    }

    private void checkRange(int number) {
        if (number <= 0 || number > 45) {
            throw new IllegalArgumentException("1과 45 사이의 수를 입력하세요.");
        }
    }
    public Lotto getLotto() {
        return lotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

}
