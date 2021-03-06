package lotto.domain;

public class BonusBall {
    public static final String SAME_NUMBER_ERROR = "보너스볼 숫자는 당첨번호와 중복될 수 없습니다";
    private final LottoNumber bonusNumber;

    public BonusBall(Lotto winLotto, String input) {
        bonusNumber = new LottoNumber(input);
        validateDuplicate(winLotto);
    }

    private void validateDuplicate(Lotto winLotto) {
        if (bonusNumber.isContain(winLotto)) {
            throw new IllegalArgumentException(SAME_NUMBER_ERROR);
        }
    }

    public boolean hasBonusBall(Lotto lotto) {
        return bonusNumber.isContain(lotto);
    }
}
