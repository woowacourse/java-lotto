package lotto.domain;

public class BonusBall {
    private static final String NUMBER_TYPE_ERROR = "[ERROR] 숫자만 입력할 수 있습니다";
    private static final String NUMBER_RANGE_ERROR = "[ERROR] 보너스볼 숫자를 1 ~ 45 사이로 입력해주세요";
    private static final String SAME_NUMBER_ERROR = "[ERROR] 보너스볼 숫자는 당첨번호와 중복될 수 없습니다";
    private int bonusBall;

    public BonusBall(Lotto winLotto, String input) {
        validateBonusBallType(input);
        validateBonusBallRange();
        validateDuplicate(winLotto);
    }

    private void validateBonusBallType(String input) {
        try {
            bonusBall = Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException(NUMBER_TYPE_ERROR);
        }
    }

    private void validateBonusBallRange() {
        if (bonusBall < 1 || bonusBall > 45) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
    }

    private void validateDuplicate(Lotto winLotto) {
        if (winLotto.isContainNum(bonusBall)) {
            throw new IllegalArgumentException(SAME_NUMBER_ERROR);
        }
    }

    public boolean hasBonusBall(Lotto lotto) {
        return lotto.isContainNum(bonusBall);
    }
}
