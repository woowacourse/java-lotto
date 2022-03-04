package domain;

import java.util.List;

public class WinningNumber {

    private static final String DUPLICATE_BONUS_BALL_MESSAGE = "[ERROR] 보너스 볼은 당첨 번호와 중복될수 없습니다.";
    
    private final Numbers winningNumbers;

    public WinningNumber(List<LottoNumber> winningNumbers) {
        this.winningNumbers = new Numbers(winningNumbers);
    }

    public void checkBonusBall(LottoNumber bonusBall) {
        if (winningNumbers.contains(bonusBall)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_BALL_MESSAGE);
        }
    }

    public boolean contains(LottoNumber number) {
        return winningNumbers.contains(number);
    }
}
