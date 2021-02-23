package lotto.domain;

public class BonusBall {
    public static final String NUMBER_TYPE_ERROR = "[ERROR] 숫자만 입력할 수 있습니다";
    public static final String NUMBER_RANGE_ERROR = "[ERROR] 보너스볼 숫자를 1 ~ 45 사이로 입력해주세요";
    public static final String SAME_NUMBER_ERROR = "[ERROR] 보너스볼 숫자는 당첨번호와 중복될 수 없습니다";
    public static final int MAXIMUM_NUMBER = 45;
    public static final int MINIMUM_NUMBER = 1;
    private int bonusNumber;

    public BonusBall(Lotto winLotto, String input) {
        validateBonusBallType(input);
        validateBonusBallRange();
        validateDuplicate(winLotto);
    }

    private void validateBonusBallType(String input) {
        try {
            bonusNumber = Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException(NUMBER_TYPE_ERROR);
        }
    }

    private void validateBonusBallRange() {
        if (bonusNumber < MINIMUM_NUMBER || bonusNumber > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
    }

    private void validateDuplicate(Lotto winLotto) {
        if (winLotto.isContainNum(bonusNumber)) {
            throw new IllegalArgumentException(SAME_NUMBER_ERROR);
        }
    }

    public boolean hasBonusBall(Lotto lotto) {
        return lotto.isContainNum(bonusNumber);
    }
}
