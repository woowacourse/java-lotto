package domain;

public record WinningNumberWithBonusNumber(
        Lotto winningNumber,
        LottoNumber bonusNumber
) {

    public WinningNumberWithBonusNumber {
        validateDistinct(winningNumber, bonusNumber);
    }

    private void validateDistinct(Lotto winningNumber, LottoNumber bonusNumber) {
        if (winningNumber.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(
                    "보너스 번호(" + bonusNumber + ")는 당첨 번호와 중복될 수 없습니다. 당첨 번호: " + winningNumber.getNumbers()
            );
        }
    }
}
