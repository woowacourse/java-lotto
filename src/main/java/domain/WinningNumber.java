package domain;

public class WinningNumber {
    private final LottoTicket winningNumbers;
    private final LottoNumber bonusBall;

    public WinningNumber(LottoTicket winningNumbers, LottoNumber bonusBall) {
        this.winningNumbers = winningNumbers;
        validateBonusBall(bonusBall);
        this.bonusBall = bonusBall;
    }

    private void validateBonusBall(LottoNumber bonusBall) {
        if (winningNumbers.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
