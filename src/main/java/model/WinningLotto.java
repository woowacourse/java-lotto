package model;

public class WinningLotto {
    private static WinningLotto INSTANCE;
    private Lotto lotto;
    private int bonusNumber;

    private WinningLotto(Lotto lotto, int bonusNumber) {
        validateBonusNumber(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public static void initialize(Lotto lotto, int bonusNumber) {
        if (INSTANCE == null) {
            INSTANCE = new WinningLotto(lotto, bonusNumber);
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

    private static void validateBonusNumber(Lotto lotto, int bonusNumber) {
        validateDuplicatedBonusNumber(lotto, bonusNumber);
        validateBonusNumberRange(bonusNumber);
    }

    private static void validateDuplicatedBonusNumber(Lotto lotto, int bonusNumber) {
        if (lotto.isBonusMatched(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }

    private static void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("숫자 범위가 벗어났습니다.");
        }
    }
}
