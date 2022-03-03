package exception.lotto;

public class BonusNumDuplicatedException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "보너스 번호는 로또 번호와 중복될 수 없습니다.";

    public BonusNumDuplicatedException() {
        super(ERROR_MESSAGE);
    }
}
