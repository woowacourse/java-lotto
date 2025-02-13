package model;

import java.util.List;

public class WinningLotto {
    private static WinningLotto INSTANCE;
    private Lotto basicLotto;
    private int bonusNumber;

    private WinningLotto(Lotto basicLotto, int bonusNumber) {
        validateBonusNumber(basicLotto, bonusNumber);
        this.basicLotto = basicLotto;
        this.bonusNumber = bonusNumber;
    }

    public static void initialize(Lotto basicLotto, int bonusNumber) {
        if (INSTANCE == null) {
            INSTANCE = new WinningLotto(basicLotto, bonusNumber);
            return;
        }
        throw new IllegalStateException("이미 당첨번호가 추첨되었습니다.");
    }

    public static WinningLotto getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("아직 당첨번호가 추첨되지 않았습니다.");
        }
        return INSTANCE;
    }

    private static void validateBonusNumber(Lotto basicLotto, int bonusNumber) {
        validateDuplicatedBonusNumber(basicLotto, bonusNumber);
        validateBonusNumberRange(bonusNumber);
    }

    private static void validateDuplicatedBonusNumber(Lotto basicLotto, int bonusNumber) {
        if (basicLotto.isBonusMatched(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }

    private static void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("숫자 범위가 벗어났습니다.");
        }
    }

    public int getMatchCount(List<Integer> numbers) {
        return basicLotto.countMatches(numbers);
    }

    public boolean isBonusMatched(List<Integer> numbers) {
        return numbers.contains(bonusNumber);
    }
}
