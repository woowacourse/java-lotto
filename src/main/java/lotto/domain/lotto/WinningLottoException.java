package lotto.domain.lotto;

public class WinningLottoException extends IllegalArgumentException {

    private static final String DUPLICATE_LOTTONUMBERS_BONUSNUMBER = "보너스 번호와 당첨 번호는 중복될 수 없습니다.";

    WinningLottoException() {
        super(DUPLICATE_LOTTONUMBERS_BONUSNUMBER);
    }
}
