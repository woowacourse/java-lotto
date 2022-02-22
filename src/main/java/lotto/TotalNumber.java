package lotto;

public class TotalNumber {

    private final WinningNumber winningNumber;
    private final LottoNumber bonusNumber;

    public TotalNumber(WinningNumber winningNumber, LottoNumber bonusNumber) {
        validateDuplication(winningNumber, bonusNumber);
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplication(WinningNumber winningNumber, LottoNumber bonusNumber) {
        if (winningNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException();
        }
    }
}
